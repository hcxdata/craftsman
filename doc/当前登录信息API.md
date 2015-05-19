# craftsman api 接口定义

##  接口规范

##  用户管理接口

### 查看当前登录用户信息

Get /api/system/loginUser

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json

    {"id":1,"name":"admin","realName":"管理员"},
~~~~

### 查看当前登录用户菜单信息

Get /api/system/loginUser/menus

#### 请求参数

|       名称      |      |    描述    |
|-----------------|------|------------|

#### 返 回 结 果
~~~~http
    HTTP/1.1 200 OK
    Content-Type: application/json

    [
        {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",children:[
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true"},
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true"},
           ...
            ]
        },
        {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true",children:[
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true"},
           {"id":1,"parentId":0,"text":"菜单","orders":1,herfTarget:"www.baidu.com",leaf:"true"},
           ...
            ]
        },
        ...
    ]
~~~~
