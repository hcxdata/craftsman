# craftsman api 接口定义

##  接口规范

##  用户管理接口

### 查看用户列表

Get /api/system/users

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `page` | 必填 | 查询当前页码 |
| `size` | 必填 | 查询条数 |
| `sort` | 非必填 | 排序(ex: name,desc)|
| `name` | 非必填 | 查询用户名称 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    
    {
    content:[
        {"id":1,"name":"admin","password":"password","realName":"管理员"},
        {"id":3,"name":"test","password":"1","realName":"test"},
        ...
    ],
    "totalPages":2,
    "last":false,
    "totalElements":11,
    "size":10,
    "number":0,
    "sort":null,
    "first":true,
    "numberOfElements":10
    }          
~~~~

### 增加用户

POST /api/system/users

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `name` | 必填 | 用户名称 |
| `password` | 必填 | 用户密码 |
| `realName` | 必填 | 展示名称 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json
    
    {"status":"500","code":"500001","message":"该用户已存在","developMessage":null,"infoLink":null}
~~~~


### 查看单个用户

GET /api/system/users/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    {"id":17,"name":"test2","password":"1","realName":"test2"}
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~

### 修改用户

PUT /api/system/users

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | 用户id |
| `name` | 必填 | 用户名称 |
| `password` | 必填 | 用户密码 |
| `realName` | 必填 | 展示名称 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"该用户已存在","developMessage":null,"infoLink":null}
~~~~


