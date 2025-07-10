import sys
from stock_api import get_stock_prices
from db import init_db, insert_prices

def main():
    if len(sys.argv) < 2:
        print("Usage: python fetch_stock.py SYMBOL")
        return

    symbol = sys.argv[1].upper()
    print(f"Fetching data for {symbol}...")

    init_db()
    prices = get_stock_prices(symbol)
    insert_prices(prices)

    print(f"Data for {symbol} has been successfully inserted into the database!")

if __name__ == "__main__":
    main()