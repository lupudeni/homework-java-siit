create schema if not exists classicmodels_test collate latin1_swedish_ci;

create table if not exists offices
(
    officeCode   varchar(10) not null
        primary key,
    city         varchar(50) not null,
    phone        varchar(50) not null,
    addressLine1 varchar(50) not null,
    addressLine2 varchar(50) null,
    state        varchar(50) null,
    country      varchar(50) not null,
    postalCode   varchar(15) not null,
    territory    varchar(10) not null
);

create table if not exists employees
(
    employeeNumber int          not null
        primary key,
    lastName       varchar(50)  not null,
    firstName      varchar(50)  not null,
    extension      varchar(10)  not null,
    email          varchar(100) not null,
    officeCode     varchar(10)  not null,
    reportsTo      int          null,
    jobTitle       varchar(50)  not null,
    constraint employees_ibfk_1
        foreign key (reportsTo) references employees (employeeNumber),
    constraint employees_ibfk_2
        foreign key (officeCode) references offices (officeCode)
);

create table if not exists customers
(
    customerNumber         int            not null
        primary key,
    customerName           varchar(50)    not null,
    contactLastName        varchar(50)    not null,
    contactFirstName       varchar(50)    not null,
    phone                  varchar(50)    not null,
    addressLine1           varchar(50)    not null,
    addressLine2           varchar(50)    null,
    city                   varchar(50)    not null,
    state                  varchar(50)    null,
    postalCode             varchar(15)    null,
    country                varchar(50)    not null,
    salesRepEmployeeNumber int            null,
    creditLimit            decimal(10, 2) null,
    constraint customers_ibfk_1
        foreign key (salesRepEmployeeNumber) references employees (employeeNumber)
);

create index salesRepEmployeeNumber
    on customers (salesRepEmployeeNumber);

create index officeCode
    on employees (officeCode);

create index reportsTo
    on employees (reportsTo);

create table if not exists orders
(
    orderNumber    int         not null
        primary key,
    orderDate      date        not null,
    requiredDate   date        not null,
    shippedDate    date        null,
    status         varchar(15) not null,
    comments       text        null,
    customerNumber int         not null,
    constraint orders_ibfk_1
        foreign key (customerNumber) references customers (customerNumber)
);

create index customerNumber
    on orders (customerNumber);

create table if not exists payments
(
    customerNumber int            not null,
    checkNumber    varchar(50)    not null,
    paymentDate    date           not null,
    amount         decimal(10, 2) not null,
    primary key (customerNumber, checkNumber),
    constraint payments_ibfk_1
        foreign key (customerNumber) references customers (customerNumber)
);

create table if not exists productlines
(
    productLine     varchar(50)   not null
        primary key,
    textDescription varchar(4000) null,
    htmlDescription mediumtext    null,
    image           mediumblob    null
);

create table if not exists products
(
    productCode        varchar(15)    not null
        primary key,
    productName        varchar(70)    not null,
    productLine        varchar(50)    not null,
    productScale       varchar(10)    not null,
    productVendor      varchar(50)    not null,
    productDescription text           not null,
    quantityInStock    smallint       not null,
    buyPrice           decimal(10, 2) not null,
    MSRP               decimal(10, 2) not null,
    constraint products_ibfk_1
        foreign key (productLine) references productlines (productLine)
);

create table if not exists orderdetails
(
    orderNumber     int            not null,
    productCode     varchar(15)    not null,
    quantityOrdered int            not null,
    priceEach       decimal(10, 2) not null,
    orderLineNumber smallint       not null,
    primary key (orderNumber, productCode),
    constraint orderdetails_ibfk_1
        foreign key (orderNumber) references orders (orderNumber),
    constraint orderdetails_ibfk_2
        foreign key (productCode) references products (productCode)
);

create index productCode
    on orderdetails (productCode);

create index productLine
    on products (productLine);

