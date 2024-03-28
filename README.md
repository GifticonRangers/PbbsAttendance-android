# PbbsAttendance :blue_heart:
<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->




<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">PbbsAttendance</h3>

  <p align="center">
    출결 후 도망가는 학생없는 수업을 만들 수 있는 희망찬 안드로이드 앱
    <br />
    <a href="https://github.com/GifticonRangers/WebServer">Web Server</a>
    ·
    <a href="https://github.com/GifticonRangers/multiple-object-tracking">AI server</a>
    ·
    <a href="https://github.com/GifticonRangers/raspberrypi-camera-server">Camera server</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Languages, libraries and tools used</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


출결 후 수업을 몰래 빠지는 학생들이 있어 정확한 출결처리에 지장을 주고 있습니다. PBBS 팀은 부정출결 문제를 해결하기 위해 새로운 출결시스템을 개발했습니다.카메라로 찍은 영상을 Object Dectecting으로 분석하고 수업의 부정출결자를 판단합니다. 또한 각 책상에 부착된 NFC 태그의 고유번호로 해당 수업 출결을 수행합니다.

Pbbs Attendance는 Pbbs 팀의 새로운 출결 시스템을 교수와 학생 모두에게 효과적으로 경험시켜줄 앱입니다.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Languages, libraries and tools used

* Kotlin
* Android Jetpack Libraries
* Coroutine
* Hilt
* Glide
* Retrofit2
* OkHttp
* Gson
* Compose

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
<!--Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources. -->
## Usage
### 1. Universal Screen
학생 또는 교수 사용자의 수업 시간표 화면.  
출석하거나 출석관리해야 하는 강의를 시간표에서 선택할 수 있습니다.  

<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/0df9ae89-31ed-46a0-b871-56e2fef75270.png" width="300" height="600"/> | <img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/e0796e52-2712-47e3-817b-d5c0e1bde12a" width="300" height="600">
  
### 2. Student-only Screen
#### a.해당 강의의 NFC 태그 출석 기능 
해당 강의의 출석해야 하는 주차와 차시를 선택 후 폰 뒷면을 nfc태그에 가까이 가져가면 nfc 출석이 완료됩니다.     
  
<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/cc52ea43-99e9-419d-b7de-19ddd4c8c613" width="300" height="600"> | <img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/02bbd809-76cd-423e-b6b4-fb7ffabe46b1" width="300" height="600"> | <img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/24496290-a854-408e-9494-65ba75160a2e" width="300" height="600"> 

#### b. 본인의 출결현황 업데이트 기능 
NFC 태그 출석을 완료하면 해당 주차에 대한 출석완료 상태를 확인할 수 있습니다.
  
<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/397ca00e-dd8c-498c-bd98-9f19613577c3" width="300" height="600"> 

#### c. 본인의 전체 출결 기록 업데이트 기능
해당 수업의 전체 주차 및 차시에 대한 출석/결석/공결/지각 처리 상태를 확인할 수 있습니다.
  
<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/397ca00e-dd8c-498c-bd98-9f19613577c3" width="300" height="600"> 
  
  
### 3. Professor-only Screen 
#### a.NFC 출석 시작 및 마감 기능 
선택한 수업의 주차와 차시를 선택해 NFC 출석을 시작할 수 있습니다.  
NFC 출석을 시작하면 NFC태그를 통해 출석하는 학생들의 인원이 실시간으로 업데이트 됩니다.  
  
<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/33edd64f-4b7d-4627-91c4-f3f3d9c58ef6" width="300" height="600"/> | <img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/c79e2f9a-e216-498c-80f3-112b75e1400d" width="300" height="600">
  
#### b.강의를 수강하는 전체 학생 명단
<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/94d3e517-ebb3-49b9-acaf-aa6a871b3d68" width="300" height="600">

#### c.해당 강의의 출석, 결석, 지각, 공결 현황 제공 기능
수업 시작 전 실시한 NFC 출석과 수업 시작 후 카메라 영상녹화를 통한 Object Detection 결과를 종합하여 내리는 부정출결 결과를 보여줍니다.  
Object Detection은 자리에 없는 학생 혹은 자리를 이탈하는 학생을 분석합니다.  
* 출석: NFC 태그로 출석한 후 강의시간 동안 자리를 이탈하지 않은 사람  
* 결석: 강의실에 오지 않아 NFC 태그를 출석하지 않았거나, NFC 태그를 출석한 후 강의실을 이탈한 사람   
* 지각: 강의실에 왔지만 NFC 태그를 출석하지 않은 사람  
* 공결: 교수가 직접 공결을 확인한 사람  
  
<img src="https://github.com/GifticonRangers/PbbsAttendance/assets/67788699/a6f95c68-f954-4557-92ff-03a418646dd0" width="300" height="600">

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

LeeYongin - 
- instagram: [0517yongin](https://instagram.com/0517yongin)
- mail: yongin0517@gmail.com

Project Link: [PbbsAttendance-android](https://github.com/GifticonRangers/PbbsAttendance-android)
Project Team Link: [풍비박산(PBBS)](https://github.com/GifticonRangers)
<p align="right">(<a href="#readme-top">back to top</a>)</p>
