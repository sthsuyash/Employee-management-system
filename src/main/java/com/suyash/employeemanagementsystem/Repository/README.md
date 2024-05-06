# Repository Interface

Repository Interfaces abstract the details of data access. Instead of directly interacting with data storage
mechanisms (such as databases, files, etc.), we interact with the repository interfaces. This allows us to define
repository interfaces to declare methods for common CRUD operations. Spring Data JPA provides a powerful way to work
with repositories without writing boilerplate code.

- `JpaRepository` is a part of Spring JPA and provides CRUD operations for entity
- The first generic parameter is the entity type and the second parameter is the type of the primary key of the entity.
  Example: `JpaRepository<Employee, Long>`
- `@Repository` annotation is used to indicate that the class provides the mechanism for storage, retrieval, search,
  update, and delete operation on objects
- The `@Repository` annotation is a specialization of the `@Component` annotation with additional features
