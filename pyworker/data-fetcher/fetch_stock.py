import sys
from stock_api import get_stock_prices
from db import init_db, insert_prices

def main():
    if len(sys.argv) < 2:
        print("用法：python fetch_stock.py SYMBOL")
        return

    symbol = sys.argv[1].upper()
    print(f"开始抓取 {symbol} 的数据...")

    init_db()
    prices = get_stock_prices(symbol)
    insert_prices(prices)

    print(f"{symbol} 的数据成功写入数据库！")

if __name__ == "__main__":
    main()