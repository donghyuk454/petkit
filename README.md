# petkit

petkit란 반려동물 간식 밀키트 온라인 커머스입니다.

# petkit 백엔드

1. Architecture
![image](https://user-images.githubusercontent.com/20418155/172601146-591edc66-01a6-4a7b-a66f-e18d9ef67778.png)

2. 주요 개발 내용
- DB 설계, 제작, 관리
- 사용자 계정, 게시물, 주문, 리뷰 등에 대한 CRUD 쿼리 제작
- Image file를 처리하기 위한 FileHandler 제작
- 개발한 프로젝트를 압축한 .war파일을 FileZilla를 활용해 Ec2 서버로 옮겨 배포

3. 특징
- 서버에 MVC 패턴의 RESTful한 API를 제작하였으며, JDBC를 통해 쿼리 작성
- 개발 이슈는 Notion 페이지를 만들어 팀원들과 공유
(관련 링크: https://resilient-ox-26f.notion.site/3b9a95b584884ebfa7d474b1850b144c?v=e811270fb0e3489e9cb74e3c386b6f19)
- 약 3개월간 개발하였으며, 팀 해체로 인해 서비스 개발 중단
- 팀 구성 ― 기획자 1, 디자이너 1, 개발자 2
