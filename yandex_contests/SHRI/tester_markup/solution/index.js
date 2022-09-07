/**
 * Сюда необходимо вставить разметку, которая будет находиться внутри тега <body>
 * ВАЖНО! тег <body> вставлять не надо, только то, что будет внутри (включая стили)
 */
const htmlFragment = `<style>
    body{
      background: #F5F5F5;
      font-family: 'Inter', sans-serif;
      font-style: normal;
      font-weight: 400;
    }
    h1{
      font-size: 18px;
      line-height: 22px;
      margin: 16px;
    }
    #goods{
      display: flex;
    }
    .item{
      background: #F8F8F8;
      border-radius: 24px;
      margin: 0 8px;
    }
    .image{
      background: #C4C4C4;
      border-radius: 16px;
      width: 149px;
      height: 138px;
      margin: 16px;
    }
    .sell-button{
      width: 149px;
      height: 35px;
      background: #FFFFFF;
      border-radius: 10px;
      font-weight: 400;
      font-size: 12px;
      line-height: 15px;
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 16px;
      color: black;
      text-decoration: none;
    }
  </style>
  <h1>Список товаров</h1>
  <div id="goods">
    <div class="item">
      <div class="image"></div>
      <a class="sell-button" href="#">Купить</a>
    </div>

    <div class="item">
      <div class="image"></div>
      <a class="sell-button" href="#">Купить</a>
    </div>

    <div class="item">
      <div class="image"></div>
      <a class="sell-button" href="#">Купить</a>
    </div>
  </div>
  `;

module.exports = function () {
    return htmlFragment;
};
