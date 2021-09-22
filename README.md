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
![Screenshot from 2021-09-22 22-29-25](https://user-images.githubusercontent.com/35868106/134418570-d99e57c6-2044-465f-a61e-f57db54001e7.png)

![Screenshot from 2021-09-22 22-30-00](https://user-images.githubusercontent.com/35868106/134418576-87fa73d3-3263-4792-b0e1-fa43d83f9ccf.png)

![Screenshot from 2021-09-22 22-30-23](https://user-images.githubusercontent.com/35868106/134418580-e39a3cee-1cad-4da8-9df8-f6508d845952.png)

## Configuration
In order to run this application, you need to get your own key from open Weather map api. You can do that by clicking [here](https://home.openweathermap.org/users/sign_up).

After you get an API key, put that key in ```gradle.properties``` file as follows:
```
API_KEY=your_key_api
