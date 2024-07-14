import React, { useEffect, useRef, useState } from "react";
import axios from "axios";
import DetailedInfo from "./DetailedInfo";

function StuInfo() {
  const [stuInfo, setStuInfo] = useState(null);
  const [detailInfo, setDetailInfo] = useState(null);
  const [searchType, setSearchType] = useState("name");
  const isEdit = useRef(false);

  const stuUrl = "http://localhost:8080/student/info";
  useEffect(() => {
    axios
      .post(stuUrl + "/list", {
        name: "",
      })
      .then((res) => {
        setStuInfo(res.data);
      });
  }, []);

  const findByName = (e) => {
    axios
      .post(stuUrl + "/list", {
        name: e,
      })
      .then((res) => {
        setStuInfo(res.data);
      });
  };
  const findByClass = (e) => {
    if (e == "") {
      findByName(e);
      return;
    }
    axios
      .post(stuUrl + "/class", {
        class: e,
      })
      .then((res) => {
        setStuInfo(res.data);
      });
  };

  const handleSearchChange = (event) => {
    if (searchType === "name") {
      findByName(event.target.value);
    } else if (searchType === "class") {
      findByClass(event.target.value);
    }
  };

  const detail = (e) => {
    console.log("e", e);
    console.log(detailInfo);
    if (detailInfo == null || e.stuId !== detailInfo.stuId) {
      isEdit.current = false;
      setDetailInfo({ ...e });
    } else {
      isEdit.current = false;
      setDetailInfo(null);
    }
    if (e.stuId === "") {
      isEdit.current = true;
    }
  };

  if (stuInfo === null) return null;
  return (
    <div>
      <div className="infoContainer">
        <div className="search-add">
          <div>
            <select onChange={(e) => setSearchType(e.target.value)}>
              <option value="name">이름</option>
              <option value="class">반</option>
            </select>
            <input
              type={searchType === "name" ? "text" : "number"}
              onChange={handleSearchChange}
              className="search"
            />
          </div>
          <div
            onClick={() => {
              detail({
                stuId: "",
                name: "",
                major: "",
                school: "",
                address: "",
                classId: "",
              });
            }}
          >
            학생 추가하기
          </div>
          <div className="infoList">
            {stuInfo.map((el) => {
              return (
                <div
                  key={el.stuId}
                  onClick={() => {
                    detail(el);
                  }}
                  className={
                    detailInfo != null && detailInfo.stuId == el.stuId
                      ? "stu-name selected"
                      : "stu-name"
                  }
                >
                  {el.name}
                </div>
              );
            })}
          </div>
        </div>
      </div>

      <div>
        {detailInfo ? (
          <DetailedInfo
            key={detailInfo.stuId}
            info={detailInfo}
            isEdit={isEdit.current}
            findByName={findByName}
          />
        ) : null}
      </div>
    </div>
  );
}
export default StuInfo;
