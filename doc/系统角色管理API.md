# craftsman api 接口定义

##  接口规范

##  角色管理接口

### 查看角色列表

Get /api/system/roles

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
        {"id":1,"name":"admin"},
        {"id":3,"name":"test"},
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

### 增加角色

POST /api/system/roles

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `name` | 必填 | 角色名称 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"该角色已存在","developMessage":null,"infoLink":null}
~~~~


### 查看单个角色

GET /api/system/roles/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    {"id":17,"name":"test2"}
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~

### 修改角色

PUT /api/system/roles/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | 角色id |
| `name` | 必填 | 角色名称 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"该角色已存在","developMessage":null,"infoLink":null}
~~~~

### 查看角色菜单
Get /api/system/roles/{roleId}/menus

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json

    [
        {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",checked:true,children:[
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",checked:false},
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",checked:true},
           ...
            ]
        },
        {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",checked:true,children:[
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",checked:false},
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",checked:true},
           ...
            ]
        },
        ...
    ]
~~~~

### 修改角色菜单

PUT /api/system/roles/{roleId}/menus

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | 角色id |
| `menus` | 必填 | 菜单id列表 |

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~
