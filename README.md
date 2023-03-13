# MongoDB Nedir?

![mongodb](https://user-images.githubusercontent.com/91599453/224635580-d329637b-6a89-413c-ba91-f5e895120ef2.png)


MongoDB, açık kaynak kodlu NoSQL türünde bir veritabanı uygulamasıdır. Bilinen ilişkisel veritabanlarının aksine daha hızlı, daha çevik, daha basit bir ortam sunar. İlişkisel veritabanlarında bulunan table yapısının karşılığı collection, row yapısı document, column yapısını ise field denilen bölümler alır. Veriler döküman halinde JSON formatında tutulur. Ölçeklenebilir bir yapıyı destekler. Daha çok hızlı şekilde veri getirilmesi gereken uygulamalarda kullanılır. Bunlardan birkaçı arama gerektiren uygulamalar, oyunlar, büyük veri uygulamaları olarak örnek verilebilir.

# 🎯 Spring Boot projesinde MongoDB kullanımı

* Oluşturduğumuz Spring Boot projemizde pom.xml dosyamıza mongodb dependency sini ekliyoruz.

``` xml
    <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
```
* Docker Compose dosyası oluşturularak mongo ve mongo express için iki farklı container oluşturuyoruz.

``` yml
version: "3"
services:
  mongo:
    image: mongo:latest
    container_name: mongocon
    restart: always
    ports:
      - '27017:27017'
    environment:
      MONGO_INITDB_ROOT_USERNAME: rootuser
      MONGO_INITDB_ROOT_PASSWORD: rootpass
  mongo-express:
    container_name: mongo-express-auth
    image: mongo-express:latest
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: rootuser
      ME_CONFIG_MONGODB_ADMINPASSWORD: rootpass
      ME_CONFIG_MONGODB_SERVER: mongo
      ME_CONFIG_MONGODB_PORT: "27017"
    ports:
      - "8075:8081"
    depends_on:
      - mongo
```

* Daha sonra application.properties dosyamız içerisinde gerekli property eklemesini yapıyoruz.

``` properties
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.username=rootuser
spring.data.mongodb.password=rootpass
spring.data.mongodb.database=mongoDB
spring.data.mongodb.port=27017
spring.data.mongodb.host=localhost
spring.data.mongodb.auto-index-creation=true
```

* Modelimizi oluşturduktan ve StudentRepository interface imizde MongoRepository sınıfını extend ettikten sonra uygulama sınıfının içerisinde argüman olarak veri girişini gerçekleştirdik. Bunun yerine service katmanı oluşturulup crud işlemleri ile de işlemler gerçekleştirilebilir.
* Bir bean oluşturup Query ve MongoTemplate sınıfları uygulama içerisinde kullanıldı.

``` java
			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));

			List<Student> students= mongoTemplate.find(query, Student.class);

			if (students.size()>1){
				throw new IllegalStateException("Bu email ile birden fazla öğrenci bulundu : " + email);
			}
			if (students.isEmpty()){
				System.out.println("Öğrenci ekleniyor : " + student);
				repository.insert(student);
			} else {
				System.out.println(student + " öğrencisi kayıtlı.");
			}
```
* 
