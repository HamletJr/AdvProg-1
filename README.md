# 🛒 AdvProg Eshop
**Nama:**   Joshua Montolalu<br>
**NPM:**    2306275746<br>
**Kelas:**  Pengjut A<br>

### Riwayat modul-modul
| [Modul 1](#modul-1) |
|---------------|

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
