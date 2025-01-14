# سیستم مدیریت کاربران

این پروژه یک نمونه برای آموزش ایجاد نرم افزار به روش مبتنی بر آزمون (TDD) می‌باشد.

لطفا این دایرکتوری را به عنوان یک پروژه در Intellij باز کنید و نیازمندی‌های خواسته شده را به روش TDD پیاده سازی کنید.

در این پروژه یک کلاس User وجود دارد که یک کاربر را نشان می‌دهد. یک کلاس UserRepository داریم که اطلاعات کاربران را در خود نگه می‌دارد و عملیات‌های اضافه کردن، حذف کردن و یا پیدا کردن اطلاعات یک کاربر را انجام می‌دهد و در نهایت یک کلاس به نام UserService داریم که به عنوان سرویس مدیریت کاربران وظیفه‌ی ثبت‌نام کاربر، احراز هویت کاربر و تعدادی سرویس‌های مدیریتی را بر عهده دارد و منطق‌های کسب و کاری در این کلاس پیاده سازی شده است.

# گزارش مراحل کار

1. ریپازیتوری اولیه بر اساس پروژه موجود ساخته شد
1. گزارش کاورج اولیه پروژه گرفته شد
1. انجام بخش اول کار (فرایند ورود با ایمیل)
   1. با رویکرد TDD تست‌ها ابتدا برای کلاس Repository نوشته شد. این کلاس چون در لایه پایین‌ترین قرار داشت از آن برای نوشتن تست‌ها شروع کردیم. نکته مهم آنکه در برخی از موارد دیده می‌شد که تست‌هایی از پیش جا مانده‌اند، به این معنا که به نظر می‌رسید می‌بایست در همان پروژه خام اولیه توسط طراح این تست‌ها نوشته می‌شدند. این موارد در قالب تست‌های `missing` در متن کامیت‌ها قابل مشاهده است
   1. در ادامه نیز برای کلاس UserService و به طور خاص فرایند ورود با ایمیل تست‌ها نوشته و حداقل کد‌های مورد نیاز هر یک نیز اضافه شد.
1. گام دوم: فرایند حذف کاربر
   1. با رویکرد TDD تست‌ها ابتدا برای کلاس UserService و UserRepository نوشته شد.
   1. در ادامه تابع removeUser در این کلاس‌ها کامل شد.
1. گام سوم: فرایند تغییر ایمیل
   1. تست کیس های فرایند تغییر ایمیل توسط کاربر اعمال شده در UserRepositoryTest.java و UserServiceTest.java.
   1. اضافه کردن فیوچهر ها مورد نظر در فایل UserService.java و UserRepository.java.
   1. اضافه کردن plugin در فایل pom.xml
