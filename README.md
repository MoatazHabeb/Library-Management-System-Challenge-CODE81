ERD
___________________________
https://www.mermaidchart.com/app/projects/2890bedd-1364-4c78-967a-74634b2a2859/diagrams/9125b84f-e41c-40c5-8f95-571b4757a35a/version/v0.1/edit


Postman collection for API testing
______________________________________________
https://api.postman.com/collections/41703067-3a54093a-681c-4abd-907f-f19583b6e9a8?access_key=PMAT-01K5ETR6E0SAZV1EFV8PM8WKZS



📚 Library Management System

A Spring Boot project with Oracle Database backend for managing a library system.
The application supports borrowing and returning books, managing authors, publishers, members, categories, and book copies, with role-based access control using Spring Security + JWT authentication.

🚀 Features
🔐 Authentication & Security

User registration and login with JWT authentication.

Passwords are securely stored using Spring Security PasswordEncoder (BCrypt).

Role-based access control:

ADMIN → Full control (add/update/delete everything, manage users).

LIBRARIAN → Manage books, authors, publishers, members, categories, and handle borrow/return transactions.

STAFF → Limited access (assist with borrowing/return, add books/copies, search).

📖 Core Modules

User Management

Create users with roles.

Login to get JWT token.

Author Management

Add, update, delete authors.

Search authors by first name.

Restricted by roles.

Book Management

Add, update, delete books.

Search books by name.

View all books.

Book Copy Management

Add copies of books with barcode.

Update or delete copies.

Search by barcode.

Category Management

Add, update, delete categories.

View all categories.

Publisher Management

Add, update, delete publishers.

Search publishers by name.

Member Management

Register library members with a national ID.

Update or delete members.

Get member details and list all members.

Borrowing Transactions

Borrow a book copy for a given period (default: 14 days).

Return a borrowed book.

Admins can view all transactions.

🛠️ Tech Stack

Backend: Spring Boot 3+, Spring Data JPA, Spring Security (JWT)

Database: Oracle

Validation: Jakarta Validation

Mapping: MapStruct

Build Tool: Maven

🔑 Security

JWT Authentication:

Login with /login to get a token.

Use the token in Authorization: Bearer <token> header for secured APIs.

Password Encoding: BCrypt used to hash and verify passwords.

PreAuthorize Annotations: Each controller endpoint is protected with role-based restrictions.

📡 API Endpoints
🔑 Auth & Users

POST /login → Login with username & password (get JWT).

POST /create-user → Create new user with role(s).

📚 Authors

POST /api/authors/addAuthor

PUT /api/authors/updateAuthorById/{id}

DELETE /api/authors/deleteAuthorById/{id}

GET /api/authors/getAllAuthors

GET /api/authors/getAuthorsByFirstName/search?name=

📘 Books

POST /api/books/addBook

PUT /api/books/updateBookById/{id}

DELETE /api/books/deleteBookById/{id}

GET /api/books/getAllBooks

GET /api/books/getBookByName/search?name=

🔖 Book Copies

POST /api/copies/addCopy

PUT /api/copies/updateCopyById/{id}

DELETE /api/copies/deleteCopyById/{id}

GET /api/copies/getAllCopies

GET /api/copies/getByBarcode/{barcode}

🏷 Categories

POST /api/categories/addCategory

PUT /api/categories/updateCategoryById/{id}

DELETE /api/categories/deleteCategoryById/{id}

GET /api/categories/getAllCategories

🏛 Publishers

POST /api/publishers/addPublisher

PUT /api/publishers/updatePublisherById/{id}

DELETE /api/publishers/deletePublisherById/{id}

GET /api/publishers/getAllPublishers

GET /api/publishers/getPublishersByName/search?name=

👤 Members

POST /api/members/createMember

PUT /api/members/updateMember/{nationalId}

DELETE /api/members/deleteMember/{nationalId}

GET /api/members/getMember/{nationalId}

GET /api/members/getAllMembers

🔄 Borrowing Transactions

POST /api/transactions/borrow?memberId=&bookCopyId=&days=

POST /api/transactions/return?bookCopyId=

GET /api/transactions/getAllTransactions (ADMIN only)

👮 Roles Example

ADMIN → Create/update/delete everything, view all transactions.

LIBRARIAN → Manage books, authors, publishers, categories, members, borrow/return.

STAFF → Assist in borrowing, return, add book copies, search.
