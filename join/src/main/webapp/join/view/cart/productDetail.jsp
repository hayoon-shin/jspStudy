<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../../headerNav.css">
    <title>상품 상세 페이지</title>
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
            margin: 40px auto;
            display: flex;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .product-image {
            width: 50%;
            overflow: hidden;
        }

        .product-image img {
            width: 100%;
            object-fit: cover;
        }

        .product-info {
            padding: 20px;
            flex: 1;
        }

        .product-info h1 {
            font-size: 28px;
            margin: 0;
            color: #333;
        }

        .product-info .tags {
            margin: 10px 0;
        }

        .product-info .tags span {
            display: inline-block;
            background: #f1c40f;
            color: #fff;
            padding: 5px 10px;
            border-radius: 3px;
            font-size: 12px;
            margin-right: 5px;
        }

        .product-info .description {
            margin: 15px 0;
            color: #666;
            font-size: 14px;
        }

        .product-info .price {
            font-size: 22px;
            font-weight: bold;
            margin: 10px 0;
            color: #e74c3c;
        }

        .product-info .price del {
            font-size: 16px;
            color: #aaa;
            margin-left: 10px;
            font-weight: normal;
        }

        .options {
            margin: 15px 0;
        }

        .options select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .actions {
            margin-top: 20px;
        }

        .actions button {
            width: 100%;
            padding: 15px;
            font-size: 16px;
            color: #fff;
            background-color: #333;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .actions button:hover {
            background-color: #555;
        }

        .actions .buy-now {
            background-color: #e74c3c;
            margin-bottom: 10px;
        }

        .actions .buy-now:hover {
            background-color: #c0392b;
        }

        .sns-link {
            margin-top: 15px;
            text-align: center;
        }

        .sns-link a {
            text-decoration: none;
            color: #333;
            background: #f1c40f;
            padding: 10px 15px;
            border-radius: 5px;
            font-size: 14px;
        }

        .sns-link a:hover {
            background: #d4ac0d;
        }
    </style>
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <div class="container">
        <!-- 상품 이미지 -->
        <div class="product-image">
            <img src="../../images/development.png" alt="개발">
        </div>

        <!-- 상품 정보 -->
        <div class="product-info">
            <h1>Programming</h1>
            <div class="tags">
                <span>인기목록</span>
                <span>MD추천</span>
            </div>
            <p class="description">Web Programming</p>
            <p class="price">
                가격문의
                <del>999,999원</del>
            </p>

            <!-- 옵션 선택 -->
            <div class="options">
                <select>
                    <option>- [필수] 옵션을 선택해 주세요 -</option>
                    <option>Standard</option>
                    <option>Deluxe</option>
                    <option>Premium</option>
                </select>
            </div>

            <!-- 버튼 -->
            <div class="actions">
                <button class="buy-now">바로구매</button>
                <button>장바구니</button>
            </div>
        </div>
    </div>
    <div class="container">
        <!-- 상품 이미지 -->
        <div class="product-image">
            <img src="../../images/design.png" alt="디자인">
        </div>

        <!-- 상품 정보 -->
        <div class="product-info">
            <h1>Design</h1>
            <div class="tags">
                <span>인기목록</span>
                <span>특가할인</span>
            </div>
            <p class="description">Web Design</p>
            <p class="price">
                가격문의
                <del>999,999원</del>
            </p>

            <!-- 옵션 선택 -->
            <div class="options">
                <select>
                    <option>- [필수] 옵션을 선택해 주세요 -</option>
                    <option>Standard</option>
                    <option>Deluxe</option>
                    <option>Premium</option>
                </select>
            </div>

            <!-- 버튼 -->
            <div class="actions">
                <button class="buy-now">바로구매</button>
                <button>장바구니</button>
            </div>
        </div>
    </div>
    <div class="container">
        <!-- 상품 이미지 -->
        <div class="product-image">
            <img src="../../images/photograph.png" alt="촬영">
        </div>

        <!-- 상품 정보 -->
        <div class="product-info">
            <h1>Photography</h1>
            <div class="tags">
                <span>인기목록</span>
                <span>MD추천</span>
            </div>
            <p class="description">Product Photography</p>
            <p class="price">
                가격문의
                <del>999,999원</del>
            </p>

            <!-- 옵션 선택 -->
            <div class="options">
                <select>
                    <option>- [필수] 옵션을 선택해 주세요 -</option>
                    <option>Standard</option>
                    <option>Deluxe</option>
                    <option>Premium</option>
                </select>
            </div>

            <!-- 버튼 -->
            <div class="actions">
                <button class="buy-now">바로구매</button>
                <button>장바구니</button>
            </div>
        </div>
    </div>
</body>
</html>
