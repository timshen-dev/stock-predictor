import yfinance as yf
from datetime import datetime, timedelta

def get_stock_prices(symbol: str, days: int = 30):
    end_date = datetime.today().date()
    start_date = end_date - timedelta(days=days)

    df = yf.download(symbol, start=start_date, end=end_date)

    prices = []
    for date, row in df.iterrows():
        prices.append({
            "symbol": symbol.upper(),
            "date": date.date(),
            "open": float(row["Open"]),
            "close": float(row["Close"]),
            "high": float(row["High"]),
            "low": float(row["Low"]),
            "volume": int(row["Volume"]),
            "created_at": datetime.now()
        })
        print(prices[0])

    return prices
