INSERT INTO role
(id, name)
VALUES("r001", 'admin');

INSERT INTO role
(id, name)
VALUES("r002", 'manager');

INSERT INTO admin
(id, nama, email, handphone, active, username,password, createdate, id_role)
VALUES(1, 'Jeremia Hays Jarfi', 'pacelinux@gmail.com', '081343439831',1, 'pace','admin', Now(),"r001");

INSERT INTO pelanggan
(id, fullname, email, handphone, username, active, createdate)
VALUES(1, 'Jeremia Hays Jarfi', 'pacelinux@gmail.com', '081343439831','pace',1, Now());

INSERT INTO password
(id, password)
VALUES(1, 'pelanggang1');

INSERT INTO permissions
(id, label, value)
VALUES("p001", 'Lihat Data Transaksi',"VIEW_TRANSAKSI");

INSERT INTO roles_permissions
(id_role, id_permission)
VALUES("r001", "p001");