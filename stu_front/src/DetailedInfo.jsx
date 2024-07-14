import axios from "axios";
import React, { useState } from "react";

function DetailedInfo({ info, isEdit, findByName }) {
  const [stuInfo, setStuInfo] = useState({
    stuId: info.stuId ? Number(info.stuId) : 0,
    name: info.name,
    major: info.major,
    school: info.school,
    classId: info.classId,
    address: info.address,
  });
  const stuUrl = "http://localhost:8080/student/info";

  const addStudent = () => {
    for (let [key, value] of Object.entries(stuInfo)) {
      if (key !== "stuId") {
        if (value === "") {
          alert("빈칸은 ㄴㄴ");
          return;
        }
      }
    }
    console.log("stuInfo", stuInfo);
    axios.post(stuUrl + "/addinfo", stuInfo).then((res) => {
      findByName("");
      alert(res.data.msg);
    });
  };

  const handleInputChange = (e, type) => {
    setStuInfo({ ...stuInfo, [type]: e.target.value });
  };

  return (
    <div className="detaileInfo">
      <div className="detailedInfoList">
        <div>이름 : &nbsp;</div>
        <input
          className={isEdit ? null : "n-focus"}
          onChange={(e) => {
            handleInputChange(e, "name");
          }}
          value={stuInfo.name}
        />
      </div>
      <div className="detailedInfoList">
        <div>전공 : &nbsp;</div>

        <input
          className={isEdit ? null : "n-focus"}
          onChange={(e) => {
            handleInputChange(e, "major");
          }}
          value={stuInfo.major}
        />
      </div>
      <div className="detailedInfoList">
        <div>학교 : &nbsp;</div>

        <input
          className={isEdit ? null : "n-focus"}
          onChange={(e) => {
            handleInputChange(e, "school");
          }}
          value={stuInfo.school}
        />
      </div>
      <div className="detailedInfoList">
        <div>주소 : &nbsp;</div>
        <input
          className={isEdit ? null : "n-focus"}
          onChange={(e) => {
            handleInputChange(e, "address");
          }}
          value={stuInfo.address}
        />
      </div>
      <div className="detailedInfoList">
        <div>반 : &nbsp;</div>
        <input
          type="number"
          placeholder="1~10반까지 입력"
          className={isEdit ? null : "n-focus"}
          onChange={(e) => {
            handleInputChange(e, "classId");
          }}
          value={stuInfo.classId}
        />
      </div>
      <button onClick={addStudent}>{isEdit ? "추가하기" : "수정하기"}</button>
    </div>
  );
}

export default DetailedInfo;
