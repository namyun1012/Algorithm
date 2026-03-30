-- 코드를 입력하세요
SELECT users.USER_ID, users.NICKNAME, CONCAT (CITY, " ", STREET_ADDRESS1, " ", STREET_ADDRESS2) AS 전체주소
, CONCAT(substring(users.TLNO, 1, 3), "-", substring(users.TLNO, 4, 4), "-", substring(users.TLNO, 8, 4)) AS 전화번호
FROM USED_GOODS_BOARD goods, USED_GOODS_USER users
WHERE goods.WRITER_ID = users.USER_ID
GROUP BY goods.WRITER_ID
HAVING count(goods.BOARD_ID) >= 3
ORDER BY users.USER_ID DESC
