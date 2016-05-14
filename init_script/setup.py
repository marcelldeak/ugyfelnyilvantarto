#!/usr/bin/python2
# -*- coding: utf-8 -*-

import MySQLdb
import random
import string
import warnings
import os
import shutil
import getpass

def main():
    print("   ____ _ _            _   ____\n  / ___| (_) ___ _ __ | |_| __ )  __ _ ___  ___\n | |   | | |/ _ \ '_ \| __|  _ \ / _` / __|/ _ \\\n | |___| | |  __/ | | | |_| |_) | (_| \__ \  __/\n  \____|_|_|\___|_| |_|\__|____/ \__,_|___/\___|")
    print("This wizard will help you configure your ClientBase instance.")
    print("Your environment must meet the following software requirements:")
    print("\t[] Java Runtime Environment 8")
    print("\t[] WildFly Application Server 10.0.0.Final")
    print("\t[] MySQL 5.7 or MariaDB 10")
    print("===================================================")
    if not os.path.isfile("./content"):
        print "Can't find database driver (content).\nPlease download it from https://github.com/codingmentor/ugyfelnyilvantarto an place it in the same directory as this script."
    print("\t\tDatabase Server Configuration")
    warnings.filterwarnings('ignore', category=MySQLdb.Warning)
    while True:
        mysql_host = raw_input("Please enter your MySQL/MariaDB host [127.0.0.1]: ");
        if mysql_host == "":
            mysql_host = "localhost"
        mysql_port = raw_input("Please enter your MySQL/MariaDB port [3306]: ")
        try:
            mysql_port = int(mysql_port)
        except ValueError:
            mysql_port = 3306
        root_pw = getpass.getpass("Please enter your  MySQL/MariaDB root password: ")
        try:
            db = MySQLdb.connect(host=mysql_host, port=mysql_port, user="root", passwd=root_pw)
            db.ping(True)
            cursor = db.cursor()
            break
        except MySQLdb.Error:
            print "Can't connect to database, please re-enter the connection settings:"

    sql_query = "CREATE DATABASE CRM_DB"
    try:
        cursor.execute(sql_query)
        cursor.close()
        cursor = db.cursor()
    except MySQLdb.Error:
        print "Can't create database CRM_DB, database might exist."
        delete_db = raw_input("Should I try to delete the database and create it again? [no]: ").lower()
        if delete_db == "yes" or delete_db == "y":
            try:
                cursor.execute("DROP DATABASE CRM_DB")
                cursor.execute(sql_query)
                cursor.close()
                cursor = db.cursor()
            except MySQLdb.Error:
                print "Error. Can't create database CRM_DB"
                db.close()
                exit(1)
        else:
            print "Exiting..."
            db.close()
            exit(0)
    print "Database CRM_DB successfully created."
    table_generation_queries = (
        "DROP TABLE IF EXISTS `CRM_DB`.`hibernate_sequence`, `CRM_DB`.`Event_Note`, `CRM_DB`.`Customer_Project`, `CRM_DB`.`Customer_Event`, `CRM_DB`.`Customer_Contact`, `CRM_DB`.`Contact_Contact_channel`, `CRM_DB`.`Application_user_User_role`, `CRM_DB`.`Application_user_Event`, `CRM_DB`.`Application_user_Contact_channel`, `CRM_DB`.`Project`, `CRM_DB`.`Note`, `CRM_DB`.`Invitation`, `CRM_DB`.`Contact`, `CRM_DB`.`Event`, `CRM_DB`.`Customer`, `CRM_DB`.`Contact_channel`, `CRM_DB`.`User_role`, `CRM_DB`.`Application_user`, `CRM_DB`.`Address`;",
        "CREATE TABLE `CRM_DB`.`Address` (   `id` bigint(20) NOT NULL,   `city` varchar(255) DEFAULT NULL,   `country` varchar(255) DEFAULT NULL,   `street` varchar(255) DEFAULT NULL,   `zip_code` varchar(255) DEFAULT NULL,   PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Application_user` (   `id` bigint(20) NOT NULL,   `first_name` varchar(255) DEFAULT NULL,   `last_name` varchar(255) DEFAULT NULL,   `picture` varchar(255) DEFAULT NULL,   `active` bit(1) NOT NULL,   `date_of_birth` date DEFAULT NULL,   `email` varchar(255) DEFAULT NULL,   `expiration_date` date DEFAULT NULL,   `password` varchar(255) DEFAULT NULL,   PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`User_role` (   `id` bigint(20) NOT NULL,   `name` varchar(255) DEFAULT NULL,   PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Contact_channel` (   `id` bigint(20) NOT NULL,   `type` varchar(255) DEFAULT NULL,   `contact_channel_value` varchar(255) DEFAULT NULL,   PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Customer` (   `id` bigint(20) NOT NULL,   `logo` varchar(255) DEFAULT NULL,   `name` varchar(255) DEFAULT NULL,   `VAT_number` varchar(255) DEFAULT NULL,   `address_id` bigint(20) DEFAULT NULL,   PRIMARY KEY (`id`),   KEY `FKfok4ytcqy7lovuiilldbebpd9` (`address_id`),   CONSTRAINT `FKfok4ytcqy7lovuiilldbebpd9` FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Event` (   `id` bigint(20) NOT NULL,   `date_of_end` datetime DEFAULT NULL,   `date_of_start` datetime DEFAULT NULL,   `name` varchar(255) DEFAULT NULL,   `type` varchar(255) DEFAULT NULL,   `address_id` bigint(20) DEFAULT NULL,   PRIMARY KEY (`id`),   KEY `FK33rwy1u815uutxkqndey8tpgc` (`address_id`),   CONSTRAINT `FK33rwy1u815uutxkqndey8tpgc` FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Contact` (   `id` bigint(20) NOT NULL,   `first_name` varchar(255) DEFAULT NULL,   `last_name` varchar(255) DEFAULT NULL,   `picture` varchar(255) DEFAULT NULL,   `customer_id` bigint(20) DEFAULT NULL,   PRIMARY KEY (`id`),   KEY `FKpsk71j0ipgawpogo62nqlfckd` (`customer_id`),   CONSTRAINT `FKpsk71j0ipgawpogo62nqlfckd` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
        "CREATE TABLE `CRM_DB`.`Invitation` (   `id` bigint(20) NOT NULL,   `description` varchar(255) DEFAULT NULL,   `event_id` bigint(20) DEFAULT NULL,   `recipient_id` bigint(20) DEFAULT NULL,   PRIMARY KEY (`id`),   KEY `FK188hpvtimp4lfjq92lnen7y8d` (`event_id`),   KEY `FK53r1n98mcyxfqak2ft3flt1s5` (`recipient_id`),   CONSTRAINT `FK188hpvtimp4lfjq92lnen7y8d` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),   CONSTRAINT `FK53r1n98mcyxfqak2ft3flt1s5` FOREIGN KEY (`recipient_id`) REFERENCES `Application_user` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Note` (   `id` bigint(20) NOT NULL,   `content` varchar(255) DEFAULT NULL,   `tag` varchar(255) DEFAULT NULL,   PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Project` (   `id` bigint(20) NOT NULL,   `deadline` date DEFAULT NULL,   `name` varchar(255) DEFAULT NULL,   `status` varchar(255) DEFAULT NULL,   PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Application_user_Contact_channel` (   `User_id` bigint(20) NOT NULL,   `contactChannels_id` bigint(20) NOT NULL,   UNIQUE KEY `UK_qa1lujegjx1da8ii57tcg4ikx` (`contactChannels_id`),   KEY `FKrwsbnqqd5lu6grci60yk6sow7` (`User_id`),   CONSTRAINT `FKrwsbnqqd5lu6grci60yk6sow7` FOREIGN KEY (`User_id`) REFERENCES `Application_user` (`id`),   CONSTRAINT `FKt1k5gs65a38dtfyu9kvl53dfk` FOREIGN KEY (`contactChannels_id`) REFERENCES `Contact_channel` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Application_user_Event` (   `User_id` bigint(20) NOT NULL,   `events_id` bigint(20) NOT NULL,   KEY `FKtqe0a2g8p91h8r6c77c9m5dg6` (`events_id`),   KEY `FKld2c        00jgrto9t02q664lbl9u9` (`User_id`),   CONSTRAINT         `FKld2c00jgrto9t02q664lbl9u9` FOREIGN KEY (`User_id`) REFERENCES `Application_user` (`id`),   CONSTRAINT `FKtqe0a2g8p91h8r6c77c9m5dg6` FOREIGN KEY (`events_id`) REFERENCES `Event` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Application_user_User_role` (   `users_id` bigint(20) NOT NULL,   `roles_id` bigint(20) NOT NULL,   KEY `FK1q2ybxcwjee2r3ngjgvr93b0r` (`roles_id`),   KEY `FKm5hpn3utwl73bmv4ofwwm02fh` (`users_id`),   CONSTRAINT `FK1q2ybxcwjee2r3ngjgvr93b0r` FOREIGN KEY (`roles_id`) REFERENCES `User_role` (`id`),   CONSTRAINT `FKm5hpn3utwl73bmv4ofwwm02fh` FOREIGN KEY (`users_id`) REFERENCES `Application_user` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Contact_Contact_channel` (   `Contact_id` bigint(20) NOT NULL,   `contactChannels_id` bigint(20) NOT NULL,   UNIQUE KEY `UK_63bwxs4ay6augyyb3qp3ngaci` (`contactChannels_id`),   KEY `FKk1sps27w03ihccn4g826je0rk` (`Contact_id`),   CONSTRAINT `FKk1sps27w03ihccn4g826je0rk` FOREIGN KEY (`Contact_id`) REFERENCES `Contact` (`id`),   CONSTRAINT `FKltdw54jd0jjo1rlurfrxskrui` FOREIGN KEY (`contactChannels_id`) REFERENCES `Contact_channel` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
        "CREATE TABLE `CRM_DB`.`Customer_Contact` (   `Customer_id` bigint(20) NOT NULL,   `contacts_id` bigint(20) NOT NULL,   UNIQUE KEY `UK_o0xq066b1svu8nty9414jdwg7` (`contacts_id`),   KEY `FKku3itqo5n1qawyiot5l7ro24` (`Customer_id`),   CONSTRAINT `FKku3itqo5n1qawyiot5l7ro24` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),   CONSTRAINT `FKlbqv4eqjwplvra01itdw6i8ec` FOREIGN KEY (`contacts_id`) REFERENCES `Contact` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
        "CREATE TABLE `CRM_DB`.`Customer_Event` (   `Customer_id` bigint(20) NOT NULL,   `events_id` bigint(20) NOT NULL,   UNIQUE KEY         `UK_cm2h0j2j5t27gtdwk8epuck3` (`events_id`),   KEY `FK5ojrppm1jssh9mrbfi5tn63qk` (`Customer_id`),   CONSTRAINT `FK5ojrppm1jssh9mrbfi5tn63qk` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),   CONSTRAINT `FKdmyhnnqh5ilfx6v6ijnxcd4h4` FOREIGN KEY (`events_id`) REFERENCES `Event` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Customer_Project` (   `Customer_id` bigint(20) NOT NULL,   `projects_id` bigint(20) NOT NULL,   UNIQUE KEY `UK_nm5gtflfu0os480hdxcb0xxyi` (`projects_id`),   KEY `FKdt72mhppcdp8m2o2yty8dw7on` (`Customer_id`),   CONSTRAINT `FKdt72mhppcdp8m2o2yty8dw7on` FOREIGN KEY (`Customer_id`) REFERENCES `Customer` (`id`),   CONSTRAINT `FKei1kfjyr2k5l7i6jw8o9wb4l` FOREIGN KEY (`projects_id`) REFERENCES `Project` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`Event_Note` (   `Event_id` bigint(20) NOT NULL,   `notes_id` bigint(20) NOT NULL,   UNIQUE KEY `UK_936gnp5eiy6x8fc5ir7ucaawg` (`notes_id`),   KEY `FKn2cknvhtuusxpdu61ouk9qdss` (`Event_id`),   CONSTRAINT `FKn0fho363ve3ufnv4c5s7nc7kn` FOREIGN KEY (`notes_id`) REFERENCES `Note` (`id`),   CONSTRAINT `FKn2cknvhtuusxpdu61ouk9qdss` FOREIGN KEY (`Event_id`) REFERENCES `Event` (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
        "CREATE TABLE `CRM_DB`.`hibernate_sequence` (   `next_val` bigint(20) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
        )
    cursor.execute("USE CRM_DB;")
    cursor.close()
    cursor = db.cursor()
    for _query in table_generation_queries:
        try:
            cursor.execute(_query)
            cursor.close()
            cursor = db.cursor()
        except MySQLdb.Error:
            print "Error. Can't create database tables."
            exit(1)
    cursor.execute("INSERT INTO `CRM_DB`.`User_role` (`id`, `name`) VALUES ('0', 'ADMIN');INSERT INTO `CRM_DB`.`User_role` (`id`, `name`) VALUES ('1', 'USER');")
    cursor.close()
    cursor = db.cursor()
    print "Database tables successfully created."
    user_pass = ''.join(random.SystemRandom().choice(string.ascii_uppercase + string.digits + string.ascii_lowercase) for _ in range(8))
    sql_query = "CREATE USER client_base@localhost IDENTIFIED BY '" + user_pass + "'"
    try:
        cursor.execute(sql_query)
        cursor.close()
        cursor = db.cursor()
    except MySQLdb.Error:
        print "Can't add user client_base, user might exist in the database."
        delete_user = raw_input("Should I try to delete the user and add it again? [no]: ").lower()
        if delete_user == "yes" or delete_user == "y":
            try:
                cursor.execute("DROP USER client_base@localhost")
                cursor.execute(sql_query)
                cursor.execute("GRANT SELECT, INSERT, UPDATE, DELETE ON `CRM_DB`.* TO client_base@localhost;")
                cursor.close()
                cursor = db.cursor()
            except MySQLdb.Error:
                print "Error. Can't add user client_base."
                db.close()
                exit(1)
        else:
            print "Exiting..."
            db.close()
            exit(0)
    print "Password for client_base user (normally, you don't need to remember this password): " + user_pass
    db.close()

    print("\t\tApplication Server Configuration")
    while True:
        wildfly_home = raw_input("Please enter your WildFly installation directory [/opt/wildfly]: ")
        if wildfly_home == "":
            wildfly_home = "/opt/wildfly"
        if wildfly_home[-1] == "/":
            wildfly_home = wildfly_home[:-1]

        #os.path.isfile() and
        if not (os.path.isfile(wildfly_home + "/standalone/configuration/standalone.xml") and os.path.isfile(
                    wildfly_home + "/standalone/configuration/standalone-full.xml") and
                    os.path.isfile(wildfly_home + "/domain/configuration/mgmt-groups.properties") and
                    os.path.isfile(wildfly_home + "/domain/configuration/mgmt-users.properties") and
                    os.path.isfile(wildfly_home + "/standalone/configuration/mgmt-users.properties") and
                    os.path.isfile(wildfly_home + "/standalone/configuration/mgmt-groups.properties")
                ):
            print "The given directory doesn't seem to be a WildFly installation directory."
        else:
            break

    sxml = '''<?xml version='1.0' encoding='UTF-8'?>
<server xmlns="urn:jboss:domain:4.0">

    <extensions>
        <extension module="org.jboss.as.clustering.infinispan"/>
        <extension module="org.jboss.as.connector"/>
        <extension module="org.jboss.as.deployment-scanner"/>
        <extension module="org.jboss.as.ee"/>
        <extension module="org.jboss.as.ejb3"/>
        <extension module="org.jboss.as.jaxrs"/>
        <extension module="org.jboss.as.jdr"/>
        <extension module="org.jboss.as.jmx"/>
        <extension module="org.jboss.as.jpa"/>
        <extension module="org.jboss.as.jsf"/>
        <extension module="org.jboss.as.jsr77"/>
        <extension module="org.jboss.as.logging"/>
        <extension module="org.jboss.as.mail"/>
        <extension module="org.jboss.as.naming"/>
        <extension module="org.jboss.as.pojo"/>
        <extension module="org.jboss.as.remoting"/>
        <extension module="org.jboss.as.sar"/>
        <extension module="org.jboss.as.security"/>
        <extension module="org.jboss.as.transactions"/>
        <extension module="org.jboss.as.webservices"/>
        <extension module="org.jboss.as.weld"/>
        <extension module="org.wildfly.extension.batch.jberet"/>
        <extension module="org.wildfly.extension.bean-validation"/>
        <extension module="org.wildfly.extension.io"/>
        <extension module="org.wildfly.extension.messaging-activemq"/>
        <extension module="org.wildfly.extension.request-controller"/>
        <extension module="org.wildfly.extension.security.manager"/>
        <extension module="org.wildfly.extension.undertow"/>
        <extension module="org.wildfly.iiop-openjdk"/>
    </extensions>


    <management>
        <security-realms>
            <security-realm name="ManagementRealm">
                <authentication>
                    <local default-user="$local" skip-group-loading="true"/>
                    <properties path="mgmt-users.properties" relative-to="jboss.server.config.dir"/>
                </authentication>
                <authorization map-groups-to-roles="false">
                    <properties path="mgmt-groups.properties" relative-to="jboss.server.config.dir"/>
                </authorization>
            </security-realm>
            <security-realm name="ApplicationRealm">
                <authentication>
                    <local default-user="$local" allowed-users="*" skip-group-loading="true"/>
                    <properties path="application-users.properties" relative-to="jboss.server.config.dir"/>
                </authentication>
                <authorization>
                    <properties path="application-roles.properties" relative-to="jboss.server.config.dir"/>
                </authorization>
            </security-realm>
        </security-realms>
        <audit-log>
            <formatters>
                <json-formatter name="json-formatter"/>
            </formatters>
            <handlers>
                <file-handler name="file" formatter="json-formatter" path="audit-log.log" relative-to="jboss.server.data.dir"/>
            </handlers>
            <logger log-boot="true" log-read-only="false" enabled="false">
                <handlers>
                    <handler name="file"/>
                </handlers>
            </logger>
        </audit-log>
        <management-interfaces>
            <http-interface security-realm="ManagementRealm" http-upgrade-enabled="true">
                <socket-binding http="management-http"/>
            </http-interface>
        </management-interfaces>
        <access-control provider="simple">
            <role-mapping>
                <role name="SuperUser">
                    <include>
                        <user name="$local"/>
                    </include>
                </role>
            </role-mapping>
        </access-control>
    </management>

    <profile>
        <subsystem xmlns="urn:jboss:domain:logging:3.0">
            <console-handler name="CONSOLE">
                <level name="INFO"/>
                <formatter>
                    <named-formatter name="COLOR-PATTERN"/>
                </formatter>
            </console-handler>
            <periodic-rotating-file-handler name="FILE" autoflush="true">
                <formatter>
                    <named-formatter name="PATTERN"/>
                </formatter>
                <file relative-to="jboss.server.log.dir" path="server.log"/>
                <suffix value=".yyyy-MM-dd"/>
                <append value="true"/>
            </periodic-rotating-file-handler>
            <logger category="com.arjuna">
                <level name="WARN"/>
            </logger>
            <logger category="org.jboss.as.config">
                <level name="DEBUG"/>
            </logger>
            <logger category="sun.rmi">
                <level name="WARN"/>
            </logger>
            <root-logger>
                <level name="INFO"/>
                <handlers>
                    <handler name="CONSOLE"/>
                    <handler name="FILE"/>
                </handlers>
            </root-logger>
            <formatter name="PATTERN">
                <pattern-formatter pattern="%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) %s%e%n"/>
            </formatter>
            <formatter name="COLOR-PATTERN">
                <pattern-formatter pattern="%K{level}%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%e%n"/>
            </formatter>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:batch-jberet:1.0">
            <default-job-repository name="in-memory"/>
            <default-thread-pool name="batch"/>
            <job-repository name="in-memory">
                <in-memory/>
            </job-repository>
            <thread-pool name="batch">
                <max-threads count="10"/>
                <keepalive-time time="30" unit="seconds"/>
            </thread-pool>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:bean-validation:1.0"/>
        <subsystem xmlns="urn:jboss:domain:datasources:4.0">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <datasource jta="true" jndi-name="java:/crmDS" pool-name="crmDS" enabled="true" use-ccm="true">
                    <connection-url>jdbc:mysql://''' + mysql_host +":" + str(mysql_port) + '''/CRM_DB</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql-connector-java-5.1.38-bin.jar_com.mysql.jdbc.Driver_5_1</driver>
                    <security>
                        <user-name>client_base</user-name>
                        <password>'''+user_pass+'''</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <background-validation>true</background-validation>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
            </datasources>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
            <deployment-scanner path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:ee:4.0">
            <spec-descriptor-property-replacement>false</spec-descriptor-property-replacement>
            <concurrent>
                <context-services>
                    <context-service name="default" jndi-name="java:jboss/ee/concurrency/context/default" use-transaction-setup-provider="true"/>
                </context-services>
                <managed-thread-factories>
                    <managed-thread-factory name="default" jndi-name="java:jboss/ee/concurrency/factory/default" context-service="default"/>
                </managed-thread-factories>
                <managed-executor-services>
                    <managed-executor-service name="default" jndi-name="java:jboss/ee/concurrency/executor/default" context-service="default" hung-task-threshold="60000" keepalive-time="5000"/>
                </managed-executor-services>
                <managed-scheduled-executor-services>
                    <managed-scheduled-executor-service name="default" jndi-name="java:jboss/ee/concurrency/scheduler/default" context-service="default" hung-task-threshold="60000" keepalive-time="3000"/>
                </managed-scheduled-executor-services>
            </concurrent>
            <default-bindings context-service="java:jboss/ee/concurrency/context/default" datasource="java:jboss/datasources/ExampleDS" jms-connection-factory="java:jboss/DefaultJMSConnectionFactory" managed-executor-service="java:jboss/ee/concurrency/executor/default" managed-scheduled-executor-service="java:jboss/ee/concurrency/scheduler/default" managed-thread-factory="java:jboss/ee/concurrency/factory/default"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:ejb3:4.0">
            <session-bean>
                <stateless>
                    <bean-instance-pool-ref pool-name="slsb-strict-max-pool"/>
                </stateless>
                <stateful default-access-timeout="5000" cache-ref="simple" passivation-disabled-cache-ref="simple"/>
                <singleton default-access-timeout="5000"/>
            </session-bean>
            <mdb>
                <resource-adapter-ref resource-adapter-name="${ejb.resource-adapter-name:activemq-ra.rar}"/>
                <bean-instance-pool-ref pool-name="mdb-strict-max-pool"/>
            </mdb>
            <pools>
                <bean-instance-pools>
                    <strict-max-pool name="slsb-strict-max-pool" derive-size="from-worker-pools" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
                    <strict-max-pool name="mdb-strict-max-pool" derive-size="from-cpu-count" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
                </bean-instance-pools>
            </pools>
            <caches>
                <cache name="simple"/>
                <cache name="distributable" passivation-store-ref="infinispan" aliases="passivating clustered"/>
            </caches>
            <passivation-stores>
                <passivation-store name="infinispan" cache-container="ejb" max-size="10000"/>
            </passivation-stores>
            <async thread-pool-name="default"/>
            <timer-service thread-pool-name="default" default-data-store="default-file-store">
                <data-stores>
                    <file-data-store name="default-file-store" path="timer-service-data" relative-to="jboss.server.data.dir"/>
                </data-stores>
            </timer-service>
            <remote connector-ref="http-remoting-connector" thread-pool-name="default"/>
            <thread-pools>
                <thread-pool name="default">
                    <max-threads count="10"/>
                    <keepalive-time time="100" unit="milliseconds"/>
                </thread-pool>
            </thread-pools>
            <iiop enable-by-default="false" use-qualified-name="false"/>
            <default-security-domain value="other"/>
            <default-missing-method-permissions-deny-access value="true"/>
            <log-system-exceptions value="true"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:io:1.1">
            <worker name="default"/>
            <buffer-pool name="default"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:infinispan:4.0">
            <cache-container name="server" default-cache="default" module="org.wildfly.clustering.server">
                <local-cache name="default">
                    <transaction mode="BATCH"/>
                </local-cache>
            </cache-container>
            <cache-container name="web" default-cache="passivation" module="org.wildfly.clustering.web.infinispan">
                <local-cache name="passivation">
                    <locking isolation="REPEATABLE_READ"/>
                    <transaction mode="BATCH"/>
                    <file-store passivation="true" purge="false"/>
                </local-cache>
                <local-cache name="persistent">
                    <locking isolation="REPEATABLE_READ"/>
                    <transaction mode="BATCH"/>
                    <file-store passivation="false" purge="false"/>
                </local-cache>
            </cache-container>
            <cache-container name="ejb" aliases="sfsb" default-cache="passivation" module="org.wildfly.clustering.ejb.infinispan">
                <local-cache name="passivation">
                    <locking isolation="REPEATABLE_READ"/>
                    <transaction mode="BATCH"/>
                    <file-store passivation="true" purge="false"/>
                </local-cache>
                <local-cache name="persistent">
                    <locking isolation="REPEATABLE_READ"/>
                    <transaction mode="BATCH"/>
                    <file-store passivation="false" purge="false"/>
                </local-cache>
            </cache-container>
            <cache-container name="hibernate" default-cache="local-query" module="org.hibernate.infinispan">
                <local-cache name="entity">
                    <transaction mode="NON_XA"/>
                    <eviction strategy="LRU" max-entries="10000"/>
                    <expiration max-idle="100000"/>
                </local-cache>
                <local-cache name="local-query">
                    <eviction strategy="LRU" max-entries="10000"/>
                    <expiration max-idle="100000"/>
                </local-cache>
                <local-cache name="timestamps"/>
            </cache-container>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:iiop-openjdk:1.0">
            <initializers transactions="spec" security="identity"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jaxrs:1.0"/>
        <subsystem xmlns="urn:jboss:domain:jca:4.0">
            <archive-validation enabled="true" fail-on-error="true" fail-on-warn="false"/>
            <bean-validation enabled="true"/>
            <default-workmanager>
                <short-running-threads>
                    <core-threads count="50"/>
                    <queue-length count="50"/>
                    <max-threads count="50"/>
                    <keepalive-time time="10" unit="seconds"/>
                </short-running-threads>
                <long-running-threads>
                    <core-threads count="50"/>
                    <queue-length count="50"/>
                    <max-threads count="50"/>
                    <keepalive-time time="10" unit="seconds"/>
                </long-running-threads>
            </default-workmanager>
            <cached-connection-manager/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jdr:1.0"/>
        <subsystem xmlns="urn:jboss:domain:jmx:1.3">
            <expose-resolved-model/>
            <expose-expression-model/>
            <remoting-connector/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jpa:1.1">
            <jpa default-datasource="" default-extended-persistence-inheritance="DEEP"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:jsf:1.0"/>
        <subsystem xmlns="urn:jboss:domain:jsr77:1.0"/>
        <subsystem xmlns="urn:jboss:domain:mail:2.0">
            <mail-session name="default" jndi-name="java:jboss/mail/Default">
                <smtp-server outbound-socket-binding-ref="mail-smtp"/>
            </mail-session>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:messaging-activemq:1.0">
            <server name="default">
                <security-setting name="#">
                    <role name="guest" delete-non-durable-queue="true" create-non-durable-queue="true" consume="true" send="true"/>
                </security-setting>
                <address-setting name="#" message-counter-history-day-limit="10" page-size-bytes="2097152" max-size-bytes="10485760" expiry-address="jms.queue.ExpiryQueue" dead-letter-address="jms.queue.DLQ"/>
                <http-connector name="http-connector" endpoint="http-acceptor" socket-binding="http"/>
                <http-connector name="http-connector-throughput" endpoint="http-acceptor-throughput" socket-binding="http">
                    <param name="batch-delay" value="50"/>
                </http-connector>
                <in-vm-connector name="in-vm" server-id="0"/>
                <http-acceptor name="http-acceptor" http-listener="default"/>
                <http-acceptor name="http-acceptor-throughput" http-listener="default">
                    <param name="batch-delay" value="50"/>
                    <param name="direct-deliver" value="false"/>
                </http-acceptor>
                <in-vm-acceptor name="in-vm" server-id="0"/>
                <jms-queue name="ExpiryQueue" entries="java:/jms/queue/ExpiryQueue"/>
                <jms-queue name="DLQ" entries="java:/jms/queue/DLQ"/>
                <connection-factory name="InVmConnectionFactory" entries="java:/ConnectionFactory" connectors="in-vm"/>
                <connection-factory name="RemoteConnectionFactory" entries="java:jboss/exported/jms/RemoteConnectionFactory" connectors="http-connector"/>
                <pooled-connection-factory name="activemq-ra" transaction="xa" entries="java:/JmsXA java:jboss/DefaultJMSConnectionFactory" connectors="in-vm"/>
            </server>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:naming:2.0">
            <remote-naming/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:pojo:1.0"/>
        <subsystem xmlns="urn:jboss:domain:remoting:3.0">
            <endpoint/>
            <http-connector name="http-remoting-connector" connector-ref="default" security-realm="ApplicationRealm"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:resource-adapters:4.0"/>
        <subsystem xmlns="urn:jboss:domain:request-controller:1.0"/>
        <subsystem xmlns="urn:jboss:domain:sar:1.0"/>
        <subsystem xmlns="urn:jboss:domain:security-manager:1.0">
            <deployment-permissions>
                <maximum-set>
                    <permission class="java.security.AllPermission"/>
                </maximum-set>
            </deployment-permissions>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:security:1.2">
            <security-domains>

                <security-domain name="secureDomain" cache-type="default">
                <authentication>
                    <login-module code="Database" flag="required">
                        <module-option name="dsJndiName" value="java:/crmDS"/>
                        <module-option name="principalsQuery" value="select password from Application_user where email=?"/>
                        <module-option name="rolesQuery" value="SELECT DISTINCT User_role.name AS 'Role', 'Roles' as 'RoleGroup' FROM Application_user_User_role join User_role ON Application_user_User_role.roles_id = User_role.id WHERE Application_user_User_role.users_id=(SELECT id FROM Application_user WHERE Application_user.email=?)"/>
                        <module-option name="hashAlgorithm" value="SHA-256"/>
                        <module-option name="hashEncoding" value="base64"/>
                    </login-module>
                </authentication>
                <authorization>
                    <policy-module code="Database" flag="required">
                        <module-option name="dsJndiName" value="java:/crmDS"/>
                        <module-option name="principalsQuery" value="select password from Application_user where email=?"/>
                        <module-option name="rolesQuery" value="SELECT DISTINCT User_role.name AS 'Role', 'Roles' as 'RoleGroup' FROM Application_user_User_role join User_role ON Application_user_User_role.roles_id = User_role.id WHERE Application_user_User_role.users_id=(SELECT id FROM Application_user WHERE Application_user.email=?)"/>
                        <module-option name="hashAlgorithm" value="SHA-256"/>
                        <module-option name="hashEncoding" value="base64"/>
                    </policy-module>
                </authorization>
            </security-domain>

                <security-domain name="other" cache-type="default">
                    <authentication>
                        <login-module code="Remoting" flag="optional">
                            <module-option name="password-stacking" value="useFirstPass"/>
                        </login-module>
                        <login-module code="RealmDirect" flag="required">
                            <module-option name="password-stacking" value="useFirstPass"/>
                        </login-module>
                    </authentication>
                </security-domain>
                <security-domain name="jboss-web-policy" cache-type="default">
                    <authorization>
                        <policy-module code="Delegating" flag="required"/>
                    </authorization>
                </security-domain>
                <security-domain name="jboss-ejb-policy" cache-type="default">
                    <authorization>
                        <policy-module code="Delegating" flag="required"/>
                    </authorization>
                </security-domain>
                <security-domain name="jaspitest" cache-type="default">
                    <authentication-jaspi>
                        <login-module-stack name="dummy">
                            <login-module code="Dummy" flag="optional"/>
                        </login-module-stack>
                        <auth-module code="Dummy"/>
                    </authentication-jaspi>
                </security-domain>
            </security-domains>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:transactions:3.0">
            <core-environment>
                <process-id>
                    <uuid/>
                </process-id>
            </core-environment>
            <recovery-environment socket-binding="txn-recovery-environment" status-socket-binding="txn-status-manager"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:undertow:3.0">
            <buffer-cache name="default"/>
            <server name="default-server">
                <http-listener name="default" socket-binding="http" redirect-socket="https"/>
                <host name="default-host" alias="localhost">
                    <location name="/" handler="welcome-content"/>
                    <filter-ref name="server-header"/>
                    <filter-ref name="x-powered-by-header"/>
                </host>
            </server>
            <servlet-container name="default">
                <jsp-config/>
                <websockets/>
            </servlet-container>
            <handlers>
                <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
            </handlers>
            <filters>
                <response-header name="server-header" header-name="Server" header-value="WildFly/10"/>
                <response-header name="x-powered-by-header" header-name="X-Powered-By" header-value="Undertow/1"/>
            </filters>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:webservices:2.0">
            <wsdl-host>${jboss.bind.address:127.0.0.1}</wsdl-host>
            <endpoint-config name="Standard-Endpoint-Config"/>
            <endpoint-config name="Recording-Endpoint-Config">
                <pre-handler-chain name="recording-handlers" protocol-bindings="##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM">
                    <handler name="RecordingHandler" class="org.jboss.ws.common.invocation.RecordingServerHandler"/>
                </pre-handler-chain>
            </endpoint-config>
            <client-config name="Standard-Client-Config"/>
        </subsystem>
        <subsystem xmlns="urn:jboss:domain:weld:3.0"/>
    </profile>

    <interfaces>
        <interface name="management">
            <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
        </interface>
        <interface name="public">
            <inet-address value="${jboss.bind.address:127.0.0.1}"/>
        </interface>
        <interface name="unsecure">
            <inet-address value="${jboss.bind.address.unsecure:127.0.0.1}"/>
        </interface>
    </interfaces>

    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
        <socket-binding name="management-http" interface="management" port="${jboss.management.http.port:9990}"/>
        <socket-binding name="management-https" interface="management" port="${jboss.management.https.port:9993}"/>
        <socket-binding name="ajp" port="${jboss.ajp.port:8009}"/>
        <socket-binding name="http" port="${jboss.http.port:8080}"/>
        <socket-binding name="https" port="${jboss.https.port:8443}"/>
        <socket-binding name="iiop" interface="unsecure" port="3528"/>
        <socket-binding name="iiop-ssl" interface="unsecure" port="3529"/>
        <socket-binding name="txn-recovery-environment" port="4712"/>
        <socket-binding name="txn-status-manager" port="4713"/>
        <outbound-socket-binding name="mail-smtp">
            <remote-destination host="localhost" port="25"/>
        </outbound-socket-binding>
    </socket-binding-group>

    <deployments>
        <deployment name="mysql-connector-java-5.1.38-bin.jar" runtime-name="mysql-connector-java-5.1.38-bin.jar">
            <content sha1="471bcc236d5d85690046eb69914c271411029ffe"/>
        </deployment>
    </deployments>
</server>'''

    try:
        f = open(wildfly_home + "/domain/configuration/mgmt-groups.properties",'w')
        f.write("admin=\n")
        f.close()
        f = open(wildfly_home + "/domain/configuration/mgmt-users.properties",'w')
        f.write("admin=c22052286cd5d72239a90fe193737253\n")
        f.close()
        f = open(wildfly_home + "/standalone/configuration/mgmt-groups.properties", 'w')
        f.write("admin=\n")
        f.close()
        f = open(wildfly_home + "/standalone/configuration/mgmt-users.properties", 'w')
        f.write("admin = c22052286cd5d72239a90fe193737253\n")
        f.close()
        f = open(wildfly_home + "/standalone/configuration/standalone-full.xml", 'w')
        f.write(sxml)
        f.close()
        if os.path.isdir(wildfly_home + "/standalone/configuration/standalone_xml_history"):
            shutil.rmtree(wildfly_home + "/standalone/configuration/standalone_xml_history")
        if not os.path.isdir(wildfly_home + "/standalone/data/" ):
            os.mkdir(wildfly_home + "/standalone/data/")
        if not os.path.isdir(wildfly_home + "/standalone/data/content"):
            os.mkdir(wildfly_home + "/standalone/data/content")
        if not os.path.isdir(wildfly_home + "/standalone/data/content/47"):
            os.mkdir(wildfly_home + "/standalone/data/content/47")
        if not os.path.isdir(wildfly_home + "/standalone/data/content/47/1bcc236d5d85690046eb69914c271411029ffe"):
            os.mkdir(wildfly_home + "/standalone/data/content/47/1bcc236d5d85690046eb69914c271411029ffe")
        shutil.copyfile("content",wildfly_home + "/standalone/data/content/47/1bcc236d5d85690046eb69914c271411029ffe/content")
    except IOError:
        print("Can't write to the WildFly installation directory, you might consider running this script as superuser.")
        exit(1)

    print "MySQL/MariaDB and WildFly succesfully configured for ClientBase."
    print "The WildFly administrator user is\nadmin:admin\nIt's recommended to change the password."

main()
try:
    pass

except:
    print "Error occured.\nExiting..."
