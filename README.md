# SerFor
Сервис предназначен для помощи в администрировании Ваших изображений.

*SerFor* определяет основные модели:
* **Realm** (*область настраиваемая под определенное приложение*)
* **Directory** (*виртуальная директория*)
* **Image** (*администрируемое изображение*)

## Realm

При регистрации Вашего приложения в сервисе, необходимо создать *Realm*.

Структура Realm:

```json
  {
    "id": null,
    "name": "ser-for",
    "realmConfig": {
      "id": null,
      "realmDir": "ser-for-directory"
    }
  }
```

*RealmConfig* - сущность определяющая конфигурацию настраиваемую область (*Realm*)

После создания Realm удалить конфигурацию (RealmConfig) отдельно от Realm невозможно, но на модификацию ограничения не наклыдываются.

### Directory

После запуска приложения произойдет создание общей папки хранения информации:

```java
Path homeDirectory = Paths.get(System.getProperty("user.home")).resolve("sor-for");

if (Files.notExists(homeDirectory)) {
    Files.createDirectory(homeDirectory);
}
```

```json
{
  ...,
  "realmConfig": {
    ...
    "realmDir": "devpav-realm"
  }
}
```

Поле realmDir определяет название физической папки определенной области (Realm) `(например: C:\Users\devpav\ser-for\devpav-realm\")`
