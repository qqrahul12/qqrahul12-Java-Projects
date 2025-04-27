 - User - name, email, password, phone, List<groupId>
 - Expense - name, type, amount, groupId, List<userId>
 - Group - name, id
 - GroupUserMapping - groupId, userId
 - GroupExpenseMapping - groupId, expenseId



# Development Notes
``@Entity`` is a decorator that marks a class as an entity in the database. It is used to define the structure of the database table and its relationships with other tables.
``@Id`` is a decorator that marks a property as the primary key of the entity. It is used to uniquely identify each record in the table.
``@GeneratedValue`` is a decorator that specifies how the primary key value should be generated. In this case, it is set to use the `GenerationType.IDENTITY` strategy, which means that the database will automatically generate a unique value for the primary key when a new record is inserted.
``@Column`` is a decorator that specifies the mapping between a class property and a database column. It can be used to define additional properties such as whether the column is nullable or unique.
``@ManyToOne`` and ``@OneToMany`` are decorators that define relationships between entities. They are used to specify how two entities are related to each other in the database.
``@JoinColumn`` is a decorator that specifies the foreign key column in the database that establishes the relationship between two entities.
``@ManyToMany`` is a decorator that defines a many-to-many relationship between two entities. It is used to specify that multiple instances of one entity can be associated with multiple instances of another entity.
``@JoinTable`` is a decorator that specifies the join table that will be used to establish the many-to-many relationship between two entities. It defines the columns in the join table that will reference the primary keys of the two entities involved in the relationship.