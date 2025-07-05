import yfinance as yf
import pandas as pd

def fetch_stock_data(symbol: str, period: str = "30d", interval: str = "1d"):
    try:
        ticker = yf.Ticker(symbol)  #创建 Ticker 对象 ，这是 yfinance 提供的类，用于操作某支股票。
        df = ticker.history(period=period, interval=interval) # 调用 yfinance.Ticker 对象的 .history() 方法，从 Yahoo Finance 拉取历史股价数据。
        df.reset_index(inplace=True)         # 把原本是索引的 Date 时间列，转换成普通的一列。
        df["Date"] = df["Date"].astype(str)  # 把 Date 列的数据类型从 datetime 转换为 str（字符串），便于返回 JSON
        return df[["Date", "Close"]].to_dict(orient="records")
    except Exception as e:
        print(f"Error fetching data for {symbol}: {e}")
        return []
    