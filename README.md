# 🛒 AdvProg Eshop
**Nama:**   Joshua Montolalu<br>
**NPM:**    2306275746<br>
**Kelas:**  Pengjut A<br>

### Riwayat modul-modul
| [Modul 1](#modul-1) | [Modul 2](#modul-2) | [Modul 3](#modul-3) |
|---------------------|---------------------| --------------------|

## Modul 1
### Reflection 1
Selama pengerjaan tutorial ini, saya berusaha untuk menerapkan prinsip-prinsip _clean coding_. Misalnya:

- Saya menggunakan nama-nama variabel yang deskriptif

- Saya menerapkan fungsi-fungsi yang sederhana dan hanya melakukan fungsi tertentu

- Saya berusaha untuk tidak menambahkan komentar yang tidak diperlukan (membuat kode sejelas mungkin agar bisa dimengerti tanpa komentar)

Namun, untuk tutorial ini, saya memutuskan untuk tidak menggunakan abstraksi pada _class_ dan _object_ karena saya merasa atribut tersebut diperlukan secara konkret. Selain itu, saya juga belum mengimplementasi _error handling_ yang baik (_raise exception_, _null values_, dan lain sebagainya), sehingga ini menjadi salah satu hal yang dapat diperbaiki. Rencana saya dalam modul-modul berikutnya adalah mengimplementasi _error handling_ dengan menambah _exception_ yang tepat untuk menangani kasus-kasus _error_.

Untuk prinsip-prinsip _secure coding_, masih ada banyak prinsip yang belum terimplementasi seperti: 
- **Autentikasi dan autorisasi**, karena belum ada sistem _user management_ yang terimplementasi sehingga ke depannya jika sudah ada implementasi _user management_ maka autentikasi dan autorisasi dapat ditambahkan.
 
- **Validasi _input_ dan _encoding output_** belum diimplementasi, sehingga ke depannya ini dapat ditambahkan untuk menghindari kemungkinan adanya serangan seperti _injection_ atau _cross-site scripting_.

### Reflection 2
1. - Setelah menulis _unit test_, saya merasa kode saya lebih aman karena ke depannya jika saya ingin _push_ suatu fitur baru, saya dapat menjalankan unit test yang sudah saya buat untuk memastikan bahwa tidak ada _bug_ atau _unwanted behavior_ baru yang muncul pada kode yang sudah saya buat sebelumnya.

   - Menurut saya, jumlah _unit test_ yang dibuat sebaiknya secukupnya saja—tidak terlalu sedikit tapi tidak terlalu banyak— untuk menguji kasus-kasus umum dan juga beberapa kasus _edge case_.

   - Salah satu metrik yang dapat digunakan untuk mengukur apakah _unit test_ kita sudah cukup adalah _**code coverage**_. _Code coverage_ merupakan persentase kode yang dijalankan oleh seluruh rangkaian _unit test_ kita, dalam satuan baris.

     - Misalnya, jika kita memiliki _code coverage_ sebanyak 50%, berarti 50% baris kode dalam proyek kita berhasil dijalankan dan diuji oleh _test suite_, sedangkan 50% sisanya tidak dijalankan. Namun, _code coverage_ 100% tidak otomatis menjamin bahwa kode kita bebas dari _bug_; _code coverage_ 100% hanya berarti _unit test_ berhasil menjalankan semua baris kode proyek kita, namun bisa saja ada _edge case_ yang belum diuji oleh unit test yang kita buat. Oleh karena itu, _unit test_ harus digunakan bersama dengan _tools_ lain untuk membantu menjaga kualitas kode kita selama proses _development_.

2. Menurut saya, jika kita membuat _class_ Java baru untuk menguji jumlah produk dalam Product List dengan rangkaian _setup_ dan _instance variable_ yang sama persis dengan file `CreateProductFunctionalTest.java`, itu akan menimbulkan masalah _duplication_ dan melanggar prinsip _clean coding_ karena sebenarnya _test_ tersebut dapat dimasukkan dalam _test suite_ yang sama, tanpa perlu membuat file yang baru.

   - Pembuatan file yang baru juga akan membingungkan orang yang membaca kode kita karena adanya pemisahan _unit test_ ini tanpa alasan yang jelas.

   - Selain itu, kita juga akan lebih sulit untuk _maintain_ unit test kita ke depannya dengan adanya ada 2 file yang berbeda untuk satu _test suite_ yang sebenarnya menguji modul/fitur yang sama.

   - Jika kita ingin menguji jumlah produk dalam Product List, sebaiknya _test_ tersebut dimasukkan ke dalam file `CreateProductFunctionalTest.java` untuk mengurangi _duplication_ dan menjaga prinsip _clean coding_.


## Modul 2
1. Untuk _exercise_ minggu ini, saya memperbaiki beberapa code quality issues yang ditemukan oleh SonarCloud, yaitu:

   - **Penghapusan _exception handling_ yang tidak diperlukan**
   ```java
   // Contoh potongan kode dari salah satu fungsi yang bermasalah dalam HomePageFunctionalTest.java
   @Test
   void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
     // Exercise
     driver.get(baseUrl);
     String pageTitle = driver.getTitle();

     // Verify
     assertEquals("ADV Shop", pageTitle);
   }
   ```
   Karena isi dari _method_ tersebut tidak dapat _throw exception_, maka deklarasi `throws Exception` di method header tidak berguna dan dapat membingungkan pembaca. Solusinya adalah menghapus bagian tersebut untuk menghasilkan method berikut:
   ```java
   // Solusi
   @Test
   void pageTitle_isCorrect(ChromeDriver driver) {
     // Exercise
     driver.get(baseUrl);
     String pageTitle = driver.getTitle();

     // Verify
     assertEquals("ADV Shop", pageTitle);
   }
   ```
   
   - **Menghapus beberapa _method_ kosong yang tidak diperlukan**
   ```java
   // Contoh potongan kode yang bermasalah dalam MainControllerTest.java
   ...
   @BeforeEach
   void setUp() {
   }
   ...
   ```
   Pada beberapa file _unit test_ yang saya buat, terdapat method `setUp()` yang dijalankan sebelum setiap _unit test_ untuk membantu menyiapkan apa yang diperlukan untuk setiap _unit test_. Namun, pada beberapa kasus proses `setUp()` ini tidak diperlukan sehingga terdapat method yang kosong seperti potongan kode di atas. Ini tidak baik karena ini membuat kode kita berantakan dan tidak rapi. Solusi yang saya terapkan adalah penghapusan _method_ `setUp()` pada beberapa file di mana itu tidak diperlukan.


   - **Menghapus _modifier_ `public` pada beberapa class yang tidak membutuhkannya**
   ```java
   // Contoh potongan kode dari definisi class yang bermasalah dalam MainControllerTest.java
   @WebMvcTest(MainController.class)
   public class MainControllerTest {
      ...
   }
   ```
   Menurut dokumentasi JUnit, sebaiknya elemen-elemen dalam _test suite_ memiliki `default` _visibility_ kecuali memang harus `public`, seperti jika kita ingin extend _test suite_ tersebut dalam class lain. Maka, saya menghapus _modifier_ `public`, seperti berikut:
   ```java
   // Potongan kode dari definisi class yang bermasalah dalam MainControllerTest.java
   @WebMvcTest(MainController.class)
   class MainControllerTest {
      ...
   }
   ```

2. Menurut saya, _workflow_ CI/CD yang sudah saya tambahkan ke repositori GitHub memenuhi definisi _Continuous Integration_ dan _Continuous Deployment_. 

   - Sesuai dengan definisi yang diberikan oleh Swaraj, _Continuous Integration_ adalah pengintegrasian dan verifikasi perubahan dan _update_ kode secara **terus-menerus dan otomatis** yang dibantu oleh _build script_ dan _tools_. Dalam kasus ini, file `ci.yml`, `sonarcloud.yml`, dan `scorecard.yml` (_build script_) menggunakan Gradle, SonarCloud, dan Scorecard (_tools_) untuk memastikan bahwa kode yang saya rancang tidak bermasalah.
   
   - Selain itu, saya juga sudah mengimplementasikan _Continuous Deployment_ dengan menggunakan PaaS Koyeb yang bersifat _pull-based_. Koyeb akan otomatis melakukan _pull_ dari repositori saya ketika ada perubahan baru dan melakukan _deploy_ ulang sehingga aplikasi web akan ter-_update_ sendiri tanpa perlu intervensi dari _developer_.

## Modul 3
1. Setelah mengerjakan modul dan exercise, saya merasa kode saya sudah memenuhi semua prinsip **SOLID**, yaitu:
   
   - **Single Responsibility Principle**<br>
   Dengan memisahkan logika `CarController` dan `ProductController`, setiap _class_ dan _file_ sekarang memiliki tanggung jawab masing-masing sehingga memudahkan proses _maintenance_ ke depannya.

   - **Open-Closed Principle**<br>
   Saya melakukan _extract interface_ dan membuat _interface_ baru, `GenericService`, sehingga _interface_ tersebut tertutup untuk modifikasi tapi terbuka untuk ekstensi. Jika ada Service baru yang ingin di-implementasikan atau ada fungsi baru yang ingin ditambahkan ke salah satu subclass Service, misalnya `ProductService` atau `CarService`, itu bisa dilakukan tanpa perlu mengubah `GenericService`.   
   
   - **Liskov Substitution Principle**<br>
   Pada awalnya, `CarController` merupakan subclass dari `ProductController`. Ini melanggar prinsip LSP karena `CarController` tidak dapat digunakan untuk mensubstitusikan `ProductController` dan sebaliknya. Oleh karena itu, saya menghapus hubungan inheritance antara `ProductController` dan `CarController` sehingga masing-masing _class_ sekarang berdiri sendiri.   

   - **Dependency Inversion Principle**<br>
   Pada awalnya, modul `CarController` (modul _high-level_) bergantung pada `CarServiceImpl`, yaitu implementasi dari `CarService`. Ini melanggar konsep DIP karena seharusnya modul _high-level_ bergantung pada abstraksi, bukan implementasi. Oleh karena itu, saya mengubah _dependency_ dalam modul `CarController` untuk bergantung kepada `CarService` sehingga ke depannya akan lebih mudah untuk mengubah detil implementasi `CarService`.

2. Keuntungan dari menerapkan prinsip **SOLID** dalam kode saya adalah:

   - Proses _maintenance_ kode lebih mudah dilakukan karena kode yang dihasilkan rapi dan terpisah dalam modul masing-masing sesuai fungsi. Contohnya adalah pemisahan `CarController` dan `ProductController` yang dipisah sehingga lebih mudah untuk menangani masalah dan mengembangkan fitur untuk masing-masing modul secara terpisah.
   
   - Proses _testing_ lebih mudah dilakukan karena setiap modul terpisah sehingga _testing_ dapat dipisah juga untuk masing-masing modul. Contohnya adalah `CarController` dan `ProductController` yang awalnya di dalam satu file dan memiliki hubungan _inheritance_, dipisah dan tidak lagi memiliki hubungan _inheritance_ sehingga setiap class dapat diuji sendiri secara independen dan terpisah antara satu sama lain.
   
   - Kode kita lebih mudah dibaca dan lebih mudah menemukan bug. Prinsip-prinsip SOLID membuat kode kita lebih modular sehingga tidak ada fungsi yang melakukan terlalu banyak hal. Ini akan menghasilkan kode yang lebih pendek dan mudah dibaca. Contohnya adalah SRP yang membatasi setiap _class_ untuk hanya melakukan satu hal saja sehingga kode yang perlu dibaca untuk memahami suatu _class_ akan lebih sedikit.

3. Kekurangan dari tidak menggunakan **SOLID** dalam kode saya adalah:

   - Kode akan lebih sulit di-_maintain_. 

   - Kode akan lebih sulit dibaca oleh orang lain. Jika kita bekerja dalam tim suatu hari, kode kita pasti akan dibaca oleh orang lain. Jika kita tidak berusaha untuk menerapkan prinsip SOLID, kita akan menyulitkan _developer_ lain untuk membaca alur jalannya kode yang kita tulis. Contohnya adalah prinsip SRP yang jika dilanggar dapat membuat fungsi kita sangat panjang dan sulit untuk dibaca.

   - Kode akan lebih sulit ditambah fitur di masa depan. Prinsip-prinsip SOLID dirancang untuk mempermudah proses modifikasi, sehingga jika prinsip-prinsip SOLID tidak diterapkan, kode kita lebih berkemungkinan untuk mengalami kerusakan atau bug saat _development_. Contohnya adalah jika class yang kita rancang tidak menerapkan OCP, kita terpaksa untuk mengubah _class_ tersebut jika ingin menambah fungsionalitas, yang dapat melanggar SRP dan juga menambah _bug_ atau _behavior_ yang tidak diinginkan.