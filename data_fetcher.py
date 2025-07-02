import yfinance as yf
import pandas as pd

def fetch_stock_data(symbol: str, period: str = "30d", interval: str = "1d"):
    try:
        ticker = yf.Ticker(symbol)
        df = ticker.history(period=period, interval=interval)
        df.reset_index(inplace=True)         # 把时间列恢复成普通列
        df["Date"] = df["Date"].astype(str)  # 把时间变成字符串，便于返回 JSON
        return df[["Date", "Close"]].to_dict(orient="records")
    except Exception as e:
        print(f"Error fetching data for {symbol}: {e}")
        return []
    