# craftsman api 接口定义

##  接口规范

##  系统菜单管理接口

### 查看第一级所有菜单

Get /api/system/menus/0/children

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    
    [
        {"id":1,"parentId":0,"text":"菜单","sorts":1,herfTarget:"www.baidu.com",leaf:"true"},
        {"id":1,"parentId":0,"text":"菜单","sorts":1,herfTarget:"www.baidu.com",leaf:"true"},
        ...
    ]
~~~~

### 查看某节点下所有菜单

Get /api/system/menus/{节点id}/children

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
    
    [
        {"id":1,"parentId":0,"text":"菜单","sorts":1,herfTarget:"www.baidu.com",leaf:"true"},
        {"id":1,"parentId":0,"text":"菜单","sorts":1,herfTarget:"www.baidu.com",leaf:"true"},
        ...
    ]
~~~~

### 增加菜单

POST /api/system/menus

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `parentId` | 必填 | 上级菜单 |
| `text` | 必填 | 菜单名称|
| `herfTarget` | 必填 | 菜单连接地址|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json
    
    {"status":"500","code":"500001","message":"该菜单已存在","developMessage":null,"infoLink":null}
~~~~


### 查看单个菜单

GET /api/system/menus/{id}

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
        {"id":1,"parentId":0,"text":"菜单","sorts":1,herfTarget:"www.baidu.com",leaf:"true"},
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"","developMessage":null,"infoLink":null}
~~~~

### 修改菜单

PUT /api/system/menus

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | 菜单id |
| `parentId` | 必填 | 上级菜单 |
| `text` | 必填 | 菜单名称|
| `herfTarget` | 必填 | 菜单连接地址|
| `sorts` | 必填 | 菜单排序位置|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json
~~~~


~~~~http
    HTTP/1.1 500 Server Error
    Content-Type: application/json

    {"status":"500","code":"500001","message":"该菜单已存在","developMessage":null,"infoLink":null}
~~~~

### 删除菜单

DELETE /api/system/menus/{id}

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

### 排序目录

PUT /api/system/menus/{id}

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

### 拖拽节点
PUT /api/system/menus/{id}

#### 请求参数
|       名称      |      |    描述    |
|-----------------|------|------------|
| `id` | 必填 | 被更改的目录id |
| `action` | 必填 | 值为"changeParent" |
| `parentId` | 必填 | 变更后的父节点id |

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
