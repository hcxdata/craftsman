# craftsman api 接口定义

##  接口规范

##  系统参数类型管理接口

### 查看参数类型列表

Get /api/system/paramTypes

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `page` | 必填 | 查询当前页码 |
| `size` | 必填 | 查询条数 |
| `sort` | 非必填 | 排序(ex: name,desc)|
| `name` | 非必填 | 查询参数类型名称 |
| `code` | 非必填 | 查询参数类型编号 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json

    {
    content:[
        {"id":1,"name":"admin","code":"123"},
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

### 增加系统参数类型

POST /api/system/paramTypes

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `name` | 必填 | 参数类型名称 |
| `code` | 必填 | 参数类型编码|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"该名称/编号的类型已存在","developMessage":null,"infoLink":null}
~~~~


### 查看单个类型

GET /api/system/paramTypes/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    {"id":17,"name":"test2","code":"1"}
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~

### 修改系统参数类型

PUT /api/system/paramTypes/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | id |
| `name` | 必填 | 名称 |
| `code` | 必填 | 编号 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"错误信息","developMessage":null,"infoLink":null}
~~~~

### 删除系统参数类型

DELETE /api/system/paramTypes/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"错误信息","developMessage":null,"infoLink":null}
~~~~


##  系统参数管理接口

### 查看参数列表

Get /api/system/params

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `page` | 必填 | 查询当前页码 |
| `size` | 必填 | 查询条数 |
| `typeCode` | 必填 | 查询参数类型id |
| `sort` | 非必填 | 排序(ex: name,desc)|
| `name` | 非必填 | 查询参数名称 |
| `code` | 非必填 | 查询参数编码 |
| `value` | 非必填 | 查询参数值 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json

    {
    content:[
        {"id":1,"name":"admin","code":"123",typeCode:"123',value:'123'},
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

### 增加系统参数

POST /api/system/params

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `typeCode` | 必填 | 参数类型code |
| `name` | 必填 | 参数名称 |
| `code` | 必填 | 参数编码|
| `value` | 必填 | 参数值|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"该名称/编号的参数已存在","developMessage":null,"infoLink":null}
~~~~


### 查看单个参数

GET /api/system/params/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json

    {"id":17,"name":"test2","code":"1",value:"123',typeCode:"2"}
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~

### 修改系统参数

PUT /api/system/params/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | id |
| `name` | 必填 | 名称 |
| `code` | 必填 | 编号 |
| `value` | 必填 | 值 |
| `typeCode` | 必填 | 类型编号 |

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

### 删除系统参数

DELETE /api/system/params/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"错误信息","developMessage":null,"infoLink":null}
~~~~
