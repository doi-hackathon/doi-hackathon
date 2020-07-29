# https://scan4kids.me
[https://scan4kids.me](https://scan4kids.me)

A full-stack web application for an actual non-profit organization, Supporters of Children with Additional Needs (S.C.A.N.), made with Spring Boot 

*	Spring Boot + Java
*	Spring Security
*	Hibernate ORM + JPA
*	Thymeleaf
*	Bootstrap CSS Framework
*	JavaScript
*	MySQL
*	Spring Integration Tests

**Libraries/Services/API**
*	jQuery
*	Filestack API
*	Stripe API
*	Email Service
*	DateTime Picker

**Features List**
*	Mobile-responsive, user-friendly
*	Sign up: Visitors can sign up to become Users via Sign Up form that implements Spring Security for duplicate usernames, passwords and mismatched passwords
*	User Dashboard: Style and functionality via Bootstrap using grid system and drop down boxes for Events the User has volunteered/RSVP to
*	Events: Admin has full CRUD abilities. Users have the ability to Read and Volunteer (including a counter) or RSVP to Events. Visitors have Read-only ability
*	Albums: Admin has full CRUD abilities, Users and Visitors have Read-only ability
*	Photos: Admin has full CRUD abilities, Users and Visitors have Read-only ability. Filestack API integration to upload photos.
*	Email Service: Contact Us form submission integrates with Email Service, tested with Mailtrap
*	Donate: Implementation of Stripe API, Visitors can make a one-time donation to S.C.A.N.
*	Custom error pages
*	Integration Tests for Events, Albums, and Photos

**Database Design**

![Database design](https://cdn.filestackcontent.com/YQ4cnqapRPS5vdrzBPXS)
