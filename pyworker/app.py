from flask import Flask, request, jsonify
# request：用于读取请求里的参数（如 URL 中的 symbol=xxx）
# jsonify：把 Python 字典/列表转成 JSON 响应，返回给前端
from flask_cors import CORS
# 当前端（如浏览器里 localhost:3000）请求你这个后端（如 localhost:5000）时，会因为域名/端口不同被浏览器阻止。加上 CORS 就可以放行。
from data_fetcher import fetch_stock_data

app = Flask(__name__)
CORS(app)  # 允许前端跨域请求

@app.route('/api/stock') # 定义一个 API 路由，前端可以通过这个路由来获取股票数据
def get_stock():
    symbol = request.args.get("symbol")   # 获取 URL 中的参数，如 AAPL
    if not symbol:
        return jsonify({"error": "Missing symbol parameter"}), 400

    data = fetch_stock_data(symbol)
    if not data:
        return jsonify({"error": "Failed to fetch stock data"}), 500

    return jsonify(data)

if __name__ == "__main__":
    app.run(debug=True)