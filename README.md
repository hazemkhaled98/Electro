# Electro
This is an e-commerce web application developed using Servlets, JSP, and ORM.

![](https://github.com/hazemkhaled98/Electro/blob/main/home.png)
![](https://github.com/hazemkhaled98/Electro/blob/main/order.png)
## Contributors:
- [Hazem Khaled](https://github.com/hazemkhaled98)
- [Moataz Mujahid](https://github.com/moatazmujahid99)
- [Yousef Ehab](https://github.com/youssef-Ehab)
## Main Functionalities
- Home Page: Landing page for the application.
- User Authentication: Users can sign up, sign in, and edit their profiles.
- Administrator Privileges: System administrator can manage product prices and quantities, and review customer profiles.
- Product Management: Users can view all available products, categorize them, and add/remove products to/from their shopping cart.
- Credit Limit Management: Users can virtually buy products within their credit limit, which is set during sign-up and can be updated later.
- Client-Side Validation: All user input is validated on the client side.
- Responsive GUI: User-friendly responsive GUI for all views (both admin & user).
- Mobile First GUI: Mobile-first GUI for user views.
- Product Search: Users can search for products by price or category.
- Persistent Shopping Cart: Shopping cart products are stored in the database if the user signs out before buying.
- Order History: The system administrator can review the customer's order history.
## Technologies Used
### Frontend:
  - HTML5
  - CSS3
  - JavaScript
### Backend:
- Servlets
- JSP
### Asynchronous Development:
- AJAX
### ORM:
- Hibernate Core (6.0.2.Final) with connection pooling
### Dependency Management:
- Maven
### Database:
- MySQL Connector/J (8.3.0)
### Security:
- Spring Security Core (6.2.2)
### JSON Processing:
- Jakarta JSON API (2.0.1, 1.1.5)
### AWS Integration:
- AWS Java SDK S3 (1.12.686)
### Connection Pooling:
- HikariCP (5.1.0)
### Email:
- Jakarta Activation (1.1.1)
- Apache Commons Email (1.6.0)
### Server:
- Tomcat 7 Maven Plugin (2.2) for deployment
### Hosting:
- AWS EC2
## Getting Started
To get a local copy up and running, follow these steps:

- Clone the repository.
- Import the project into your IDE.
- Set up your Java development environment.
- Set up your database and create a user with credentials specified in the persistance.xml file for hibernate connection then import the schema.
- Create an S3 bucket then create an aws.properties file with bucket name, access key, and secret key, and place this file in the resources folder for uploading images.  
- Set up a tomcat server and deploy the application using the command `mvn clean tomcat7:redeploy`.
