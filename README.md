Library Loan System
------
Basic info:
- Author: Andrii Dovbush

- Used technologies: Java, Spring/Spring Boot, Hibernate, Thymeleaf, JUnit,
  PosgreSQL, Lombok, HTML5

- IDE: Intellij IDEA

Description:
Database management system for library. Allows you to register/delete clients,
register/delete items, and create/read/edit/delete loans.
 
Loans can have multiple items. Clients can have multiple loans.
If you delete client - all his loans will be deleted, items will be
available in list again.
Items that are currently in loan, are not available for another loan.

Attention:
Before use, set application properties.

GitHub:  https://github.com/dovbushandrii/LibrarySystem

Licence
-------

GNU General Public License v3.0
Permissions of this strong copyleft license are conditioned on making available complete source code of 
licensed works and modifications, which include larger works using a licensed work, under the same license.
Copyright and license notices must be preserved. Contributors provide an express grant of patent rights.