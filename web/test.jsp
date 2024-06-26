<%-- 
    Document   : test
    Created on : Jun 26, 2024, 3:25:12 PM
    Author     : LNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkbox with Label Inside</title>
        <style>
            .checkbox-container {
                display: inline-block;
                position: relative;
                cursor: pointer;
                font-size: 22px;
                user-select: none;
            }

            .checkbox-container input {
                position: absolute;
                opacity: 0;
                cursor: pointer;
                height: 100%;
                width: 100%;
                margin: 0;
            }

            .checkmark {
                border: 1px solid #ccc;
                padding: 10px;
                margin: 5px;
                width: 25%;
                text-align: center;
                border-radius: 5px;
                position: absolute;
                top: 0;
                left: 0;
                height: 25px;
                width: 25px;
                background-color: #eee;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .checkbox-container input:checked ~ .checkmark {
                background-color: #2196F3;
            }

            .checkmark:after {
                content: "${i}";
                color: white;
                font-size: 16px;
                display: none;
            }

            .checkbox-container input:checked ~ .checkmark:after {
                display: block;
            }
        </style>
    </head>
    <body>

        <label class="checkbox-container">
            <input type="checkbox" id="data-variant-p4" name="sizeFilter" value="${i}" class="filter-list-check">
            <span class="checkmark">10</span>
        </label>

    </body>
</html>
