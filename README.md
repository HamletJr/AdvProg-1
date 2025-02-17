# 🛒 AdvProg Eshop
**Nama:**   Joshua Montolalu<br>
**NPM:**    2306275746<br>
**Kelas:**  Pengjut A<br>

### Riwayat modul-modul
| [Modul 1](#modul-1) | [Modul 2](#modul-2) |
|---------------------|---------------------|

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
   