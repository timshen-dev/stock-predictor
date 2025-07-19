from db import init_db
from models import StockPrice
from sqlalchemy import create_engine, func
from sqlalchemy.orm import sessionmaker

# 初始化数据库连接
init_db()
engine = create_engine("postgresql://tianyishen@localhost/stockdb")
Session = sessionmaker(bind=engine)
session = Session()

# 查询所有 symbol 的最早和最晚日期
results = (
    session.query(
        StockPrice.symbol,
        func.min(StockPrice.date),
        func.max(StockPrice.date)
    )
    .group_by(StockPrice.symbol)
    .order_by(StockPrice.symbol)
    .all()
)

if not results:
    print("No data found in the database.")
else:
    print("📈 Existing stock data in the database:")
    for symbol, start_date, end_date in results:
        print(f"- {symbol} ({start_date} ~ {end_date})")

session.close()