# WeatherForecast
The user type a city name and with the help of openWeatherMaps Api and that's the link of the API https://openweathermap.org/api

## Features
- give a current Temperature degree in celsius for any location on Earth.
- give the overall state of city weather

## Architecture and Tech features
- Fully written in Kotlin language.
- Uses Retrofit for making API calls.
- Uses Picasso for image loading.

## Screenshots
![image](https://user-images.githubusercontent.com/35868106/135733896-e0313629-e80d-4d34-929d-5854be1795d1.png)

![image](https://user-images.githubusercontent.com/35868106/135733919-94c28323-6b4a-4b96-b53d-9976b3aa0fb3.png)

![image](https://user-images.githubusercontent.com/35868106/135733928-21a05743-1b48-4e1b-8e6e-bf03301c1cf5.png)

## Configuration
In order to run this application, you need to get your own key from open Weather map api. You can do that by clicking [here](https://home.openweathermap.org/users/sign_up).

After you get an API key, put that key in ```gradle.properties``` file as follows:
```
API_KEY=your_key_api
