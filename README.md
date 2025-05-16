Daftar smell code yang telah ditemukan:

smell code	: bloaters - long method,
penyebab		: developer hanya menambahkan code ke function main,
solusi		: extract method

smell code	: dispensables - comments,
penyebab		: masih ada comments untuk menjelaskan cara kerja bagian code,
solusi		: extract method

smell code	: dispensables - data class,
penyebab		: ada public field,
solusi		: encapsulate field

smell code	: dispensables - duplicate code,
penyebab		: ada bagian code yang memiliki fungsi mirip dengan bagian code yang lain,
solusi		: extract method

smell code	: bloaters - long parameter list,
penyebab		: ada lebih dari 4 parameter untuk suatu method,
solusi		: introduce parameter object

smell code	: dispensables - dead code,
penyebab		: ada bagian code yang tidak memiliki fungsi apa pun,
solusi		: hapus dead code
