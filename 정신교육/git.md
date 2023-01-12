## 콘솔에서의 깃 사용법 (연동 생략)

![git의 구조](./images/git.jpg)

원격(remote, 깃헙에 저장된 것을 뜻함) 리포지토리를 로컬(내 컴퓨터)로 clone 하고, Eclipse와 연동한 이후 프로세스

![폴더 이동](./images/change_directory.png)

1. 먼저 `clone` 땡긴 리포지토리 폴더로 이동한다(하위에 `.git` 파일이 있음).
    ``` bash
    cd 이동할 디렉토리 경로
    ```

![git pull](./images/pull.png)

2. 로컬 브랜치의 변경사항이 없다면 `git pull` 명령어를 사용하여 로컬을 최신화한다.

    유저 정보가 등록되어 있지 않아 `pull`이 안 된다면 아래처럼 유저 정보를 설정한다.

    ``` bash
    git config --local user.name "유저이름"
    git config --local user.email "깃헙 이메일"
    ```

![git status](./images/status_add_commit.png)

3. 변경사항이 있다면 먼저 작업 내용을 `add`(스테이징), `commit`(변경내역 확정)한다.

    ``` bash
    # 현재 수정사항 등의 상태 출력
    git status
    # .(dot)은 모든 변경사항을 스테이징 하겠다는 의미
    git add .
    # vscode가 열리면 내용 작성하고 저장, 닫기
    git commit
    ```

![push reject](./images/push_reject.png)

4. 변경사항이 로컬 리포지토리에 반영되었으면, 원격의 최신화 여부를 확인하고, 원격 변경사항이 있을 경우 `merge`(병합)한다.

    ``` bash
    # fetch는 원격 변경사항을 가져오기만 하고 합치지는 않은 상태
    git fetch
    # 원격 변경사항과 합치는 작업 (기준 브랜치에 주의해야 하나 현재는 main만 사용하여 생략)
    git merge
    # 원격 파일과 로컬 파일이 모두 수정되어 conflict가 발생할 경우, vscode로 일일이 원격과 로컬의 변경사항 확인하여 수정
    git mergetool
    # conflict를 해결하고 merge 작업을 계속 진행
    git merge --continue
    ```

5. 원격과 로컬 모두 최신화 되었으니 로컬의 정보를 `push`한다.

    ``` bash
    git push
    # upstream이 등록되어 있지 않은 경우 (브랜치를 새로 팠다거나)
    git push origin [로컬브랜치명]
    # 혹은 set-upstream-to 옵션을 사용하여 원격과 연동 후 push
    git branch --set-upstream-to=origin/[원격에서 사용할 브랜치명] [로컬브랜치명]
    git push
    ```
