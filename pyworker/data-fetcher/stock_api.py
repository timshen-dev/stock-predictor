import yfinance as yf
from datetime import datetime, timedelta

def get_stock_prices(symbol: str, days: int = 30):
    end_date = datetime.today().date()
    start_date = end_date.replace(day=1) if days >= 30 else end_date - timedelta(days=days)

    df = yf.download(symbol, start=start_date, end=end_date)

    prices = []
    for date, row in df.iterrows():
        prices.append({
            "symbol": symbol.upper(),
            "date": date.date(),
            "open": row["Open"],
            "close": row["Close"],
            "high": row["High"],
            "low": row["Low"],
            "volume": int(row["Volume"]),
        })

    return prices