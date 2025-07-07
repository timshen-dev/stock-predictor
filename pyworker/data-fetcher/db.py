import os
from dotenv import load_dotenv
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from models import Base, StockPrice

load_dotenv()

DB_URL = f"postgresql://{os.getenv('DB_USER')}:{os.getenv('DB_PASSWORD')}@{os.getenv('DB_HOST')}:{os.getenv('DB_PORT')}/{os.getenv('DB_NAME')}"
engine = create_engine(DB_URL)
SessionLocal = sessionmaker(bind=engine)

def init_db():
    Base.metadata.create_all(engine)

def insert_prices(prices: list[dict]):
    session = SessionLocal()
    try:
        for p in prices:
            record = StockPrice(**p)
            session.merge(record)  # 冲突时自动 update（基于 UNIQUE 限制）
        session.commit()
    except Exception as e:
        session.rollback()
        raise e
    finally:
        session.close()