### add product
```
curl --location --request POST 'http://localhost:8082/api/product/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productName":"iphone 8",
    "productSKU":"zxc",
    "productDescription":"released on 2020",
    "productPrice":80000,
    "productWeight":200,
    "productCategory" : "phone"
}'
```

### get
```
curl --location --request GET 'http://localhost:8081/auth/api/product/list'\
```

### delete
```
curl --location --request DELETE 'http://localhost:8081/auth/api/product/delete/2'\
```

### update

```
curl --location --request POST 'http://localhost:8081/auth/api/product/update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId":2,
    "productName":"iphone 13",
    "productSKU":"zxc",
    "productDescription":"released on 2020",
    "productPrice":80000,
    "productWeight":200,
    "productCategory" : "phone"
}'
```
