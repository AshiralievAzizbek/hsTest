Тестовое задание.
Задача:
    ● Главный экран приложения доставки еды, дизайн по ссылке выше 
    ● В баннеры можно захардкодить любые фото 
    ● Основная задача - сделать идентичную планку с категориями и блок меню
    ● Планка с категориями при скролле должна прилипать к верхнему бару(для примера можно посмотреть приложение Додо Пицца). 
    ● В качестве API использовать любой открытый источник подходящий под текущие нужды Опционально: 
    ● Offline-режим: т.е. в случае, если нет доступа к сети, показывать последние загруженные данные(и ленту, и детали).

Ограничения на стек технологий:
● MVVM 
● Clean Architecture 
● Остальное на ваше усмотрение

Что получилочь в итоге.
В проекте использованы: MVVM, ViewModels, Kotlin-coroutines / Flow, Retrofit2, Glide, Room, Hilt.

В Clean не смог, не стал пытаться прыгнуть выше головы, написал приложение так как умею на данный момент
Категории пришлось захардкодить тк не нашел подходящую API
Фото в баннерах тоже захардкожены
Возникли проблемы с паддингом у айтемов категорий, TabItem с кастомным атрибутом tabBackground не дружит с tabPadding(со всеми), прописал паддинги LayoutParams в коде HomeFragment(все отоброжается правильно, но есть очень высокая вероятность того что это говно-код)


