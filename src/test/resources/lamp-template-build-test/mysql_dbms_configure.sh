#!/bin/bash
cat << EOF | mysql -h ${database_host} -P ${database_port} -u root --password=${database_password}
USE ${database_name};
create table tasks (id INT not null auto_increment,task varchar(255), primary key(id));
EXIT
EOF
