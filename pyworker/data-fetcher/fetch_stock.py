# fetch_stock.py
import yfinance as yf
from db_utils import get_connection
import sys
from datetime import datetime, timedelta

def fetch_stock_data(symbol):
    end = datetime.today()
    start = end - timedelta(days=30)
    df = yf.download(symbol, start=start.strftime('%Y-%m-%d'), end=end.strftime('%Y-%m-%d'))
    df.reset_index(inplace=True)
    return df

def save_to_postgres(symbol, df):
    conn = get_connection()
    cursor = conn.cursor()
    for _, row in df.iterrows():
        cursor.execute("""
            INSERT INTO stock_prices (symbol, trade_date, open, high, low, close, volume)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
            ON CONFLICT (symbol, trade_date) DO NOTHING
        """, (
            symbol,
            row['Date'].date(),
            row['Open'],
            row['High'],
            row['Low'],
            row['Close'],
            int(row['Volume'])
        ))
    conn.commit()
    cursor.close()
    conn.close()
    print(f"✅ 插入 {len(df)} 条数据")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("❌ 用法: python fetch_stock.py AAPL")
        sys.exit(1)

    symbol = sys.argv[1].upper()
    df = fetch_stock_data(symbol)
    save_to_postgres(symbol, df)