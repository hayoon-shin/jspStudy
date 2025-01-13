<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>상품 리스트</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #121212;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 20px auto;
            text-align: center;
        }

        h1 {
            font-size: 24px;
            color: #ffffff;
            margin-bottom: 10px;
        }

        p.subtitle {
            font-size: 14px;
            color: #ffffff;
            margin-bottom: 30px;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            justify-content: center; /* 가로 가운데 정렬 */
            align-items: center; /* 세로 가운데 정렬 */
        }

        .product {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }

        .product:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .product img {
            width: 100%;
            height: 250px;
            object-fit: cover;
        }

        .product-info {
            padding: 15px;
            text-align: center;
        }

        .product-info h2 {
            font-size: 18px;
            color: #333;
            margin: 0;
            margin-bottom: 10px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .product-info p {
            font-size: 14px;
            color: #666;
            margin: 0;
        }
    </style>
    <link rel="stylesheet" href="../../headerNav.css">
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <div class="container">
        <h1>BEST ITEMS</h1>
        <p class="subtitle">당신이 원하는 서비스를 만들어줄 3가지 아이템</p>

        <div class="grid">
            <!-- 하드코딩된 상품 데이터 -->
            <div class="product">
                <img src="../../images/development.png" alt="상품 1">
                <div class="product-info">
                    <h2>Web Programming</h2>
                    <p>999,999원</p>
                </div>
            </div>
            <div class="product">
                <img src="../../images/design.png" alt="상품 2">
                <div class="product-info">
                    <h2>Web Design</h2>
                    <p>999,999원</p>
                </div>
            </div>
            <div class="product">
                <img src="../../images/photograph.png" alt="상품 3">
                <div class="product-info">
                    <h2>Product Photograph</h2>
                    <p>999,999원</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
