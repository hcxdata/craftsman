# craftsman api 接口定义

##  接口规范

##  数据字典类型管理接口

### 查看字典类型列表

Get /api/system/dictTypes

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `page` | 必填 | 查询当前页码 |
| `size` | 必填 | 查询条数 |
| `sort` | 非必填 | 排序(ex: name,desc)|
| `name` | 非必填 | 查询字典类型名称 |
| `code` | 非必填 | 查询字典类型编号 |

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

### 增加数据字典类型

POST /api/system/dictTypes

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `name` | 必填 | 字典类型名称 |
| `code` | 必填 | 字典类型编码|

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

GET /api/system/dictTypes/{id}

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

### 修改数据字典类型

PUT /api/system/dictTypes/{id}

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

### 删除数据字典类型

DELETE /api/system/dictTypes/{id}

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


##  数据字典管理接口

### 查看字典列表

Get /api/system/dicts

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `page` | 必填 | 查询当前页码 |
| `size` | 必填 | 查询条数 |
| `typeCode` | 必填 | 查询字典类型id |
| `sort` | 非必填 | 排序(ex: name,desc)|
| `name` | 非必填 | 查询字典名称 |
| `code` | 非必填 | 查询字典编码 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    
    {
    content:[
        {"id":1,"name":"admin","code":"123",typeCode:'123',orders:'2'},
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

### 增加数据字典

POST /api/system/dicts

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `typeId` | 必填 | 字典类型id |
| `name` | 必填 | 字典类型名称 |
| `code` | 必填 | 字典类型编码|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json
    
    {"status":"500","code":"500001","message":"该名称/编号的字典已存在","developMessage":null,"infoLink":null}
~~~~


### 查看单个字典

GET /api/system/dicts/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    
    {"id":17,"name":"test2","code":"1",typeCode:"2",orders:"2"}
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~

### 修改数据字典

PUT /api/system/dicts/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | id |
| `name` | 必填 | 名称 |
| `code` | 必填 | 编号 |
| `typeCode` | 必填 | 类型编号 |
| `orders` | 必填 | 排序 |

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

### 删除数据字典

DELETE /api/system/dicts/{id}

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

### 排序数据字典

PUT /api/system/dicts/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | id |
| `action` | 必填 | 值为`order` |
| `direction` | 必填 | 操作：可选值为['up','down'] |

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


