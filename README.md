# Matcherloper (개발자 매칭 프로젝트) [![Build Status](https://app.travis-ci.com/MatcherLoper/MatcherLoper_Server.svg?token=hyUJYqs7AAdXxo2iuzQs&branch=develop)](https://app.travis-ci.com/MatcherLoper/MatcherLoper_Server)

## 🤷‍♂️서비스 소개

"저는 초보 개발자인데 프로젝트를 할 수 있는 마땅한 기회가 없어요!!😂"

때마침, 나타난 `Matcherloper`⁉

</br >

`Matcherloper`는 개발자들이 프로젝트를 경험할 수 있는 기회를 제공합니다.

특히, 프로젝트 경험이 별로 없는 많은 주니어 개발자들에게 필요한 어플입니다.

</br >

한 `사용자`(creatUser)가 필요한 `포지션의 수`와 `방 이름`을 명시한 `방`을 형성하고 그 외 방을 참여하고 싶은 `사용자`(participantUser)는 `매칭`을 통해 선택한 포지션에 맞는 빈 방을 찾아 랜덤으로 방에 참여할 수 있도록 합니다.

</br >

## 🖐개발 기간

- 2021.07.21 ~ 2021.09.29

</br >

## 📚기술 스택

- Kotlin
- Retrofit2 2.9.0
- recyclerView 1.2.1
- navigation 2.3.5
- Firebase Messaging
- databinding
- viewmodel 2.3.1
- LiveData

</br >

## 🍻기능

### 회원

- 회원가입 및 로그인
  - 로그인 시 토큰을  발급받습니다.
- 방을 생성할 수 있습니다.
  - 필요한 포지션의 수, 오프라인 가능 지역 등 정보를 입력할 수 있습니다.
- 방에 참여할 수 있습니다.
  - 참여할 회원의 포지션을 정해 랜덤으로 매칭되도록 합니다.

</br >

### 방 참여

- FCM을 통해 새로운 방이 생성되거나 방의 상태가 OPEN으로 바뀌었을 때 매칭중인 개발자에게 알림이 가도록 하여 방을 참여할 수 있습니다.

