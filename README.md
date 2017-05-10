# RBAC权限管理

### 一、RBAC0模型
1. 定义：
- RBAC0模型由以下描述确定：
- U、R、P、S分别表示用户集合、角色集合、许可权集合和会话集合。
- PA P×R表示许可权与角色之间多对多的指派关系。
- UA U×R表示用户与角色之间多对多的指派关系。
- 用户：S→U每个会话si到单个用户user(si)的映射函数（常量代表会话的声明周期）。
- 角色：S→2每个会话si到角色子集roles(si) {r|user(si, r')∈UA}（能随时间改变）的映射函数，会话si有许可权Ur∈roles(si){p|(p,r')∈PA}。
2. 在使用RBAC0模型时，应该要求每个许可权和每个用户至少应该被分配给一个角色。两个角色被分配的许可权完全一样是可能的，但仍是两个完全独立的角色，用户也有类似情况。角色可以适当的被看做是一种语义结构，是访问控制策略形式化的基础。
3. RBAC0把许可权处理未非解释符号，因为其精确含义只能由实现确定且与系统有关。RBAC0中的许可权只能应用于数据和资源类客体，但不能应用于模型本身的组件。修改集合U、R、P和关系PA和UA的权限称为管理权限，后面将介绍RBAC的管理模型。因此，在RBAC0中假定只有安全管理员才能修改这些组件。
4. 会话是由单个用户控制的，在模型中，用户可以创建会话，并有选择的激活用户角色的某些子集。在一个会话中的角色的激活是由用户来决断的，会话的终止也是由用户初始化的。RBAC0不允许由一个会话去创建另一个会话，会话只能由用户创建。
5. RBAC支持三个著名的安全原则：最小权限原则，责任分离原则和数据抽象原则。

### 二、数据库建模
- 1. 角色一共有三种，分别是管理员admin，普通用户user和游客visitor
- 2. 权限一共有4种，分别可以操作按钮0，按钮1，按钮2，按钮3
- 3. 用户和角色的对应关系是，Bob既是管理员又是用户，Tom只是普通用户，Peter是游客。
- 4. 角色和权限的对应关系是，管理员可以操作所有按钮，普通用户可以操作前三个按钮，游客可以操作前两个按钮。
- 5. 用户表和角色表的主键作为用户角色关联表的外键，同时也是联合主键；角色表和权限表与角色权限关联表的关系类似。

### 三、系统实现
1. 数据库
```sql
# 建立role角色表
CREATE TABLE `rbac`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`),
  UNIQUE INDEX `idrole_UNIQUE` (`idrole` ASC),
  UNIQUE INDEX `rolename_UNIQUE` (`rolename` ASC));

# 添加角色
INSERT INTO `rbac`.`role` (`idrole`, `rolename`) VALUES ('0', 'admin');
INSERT INTO `rbac`.`role` (`rolename`) VALUES ('user');
INSERT INTO `rbac`.`role` (`rolename`) VALUES ('visitor');
# 其他表的建立类似

# 建立role-user用户角色关联表
CREATE TABLE `rbac`.`role-user` (
  `idrole` INT NOT NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`idrole`, `iduser`),
  INDEX `iduser_idx` (`iduser` ASC),
  CONSTRAINT `iduser`
    FOREIGN KEY (`iduser`)
    REFERENCES `rbac`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idrole`
    FOREIGN KEY (`idrole`)
    REFERENCES `rbac`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
# 角色权限关联表类似的建立
```