from flask import Flask, request, jsonify
from flask_cors import CORS
from data_fetcher import fetch_stock_data

app = Flask(__name__)
CORS(app)  # 允许前端跨域请求

@app.route('/api/stock')
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