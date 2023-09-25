/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./models/Candidate.ts":
/*!*****************************!*\
  !*** ./models/Candidate.ts ***!
  \*****************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\nvar __importDefault = (this && this.__importDefault) || function (mod) {\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\n};\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nconst User_1 = __importDefault(__webpack_require__(/*! ./User */ \"./models/User.ts\"));\nclass Candidate extends User_1.default {\n    constructor({ id = 0, name = \"default\", email = \"default@gmail.com\", competencies = [\"\"], age = 0 } = {}) {\n        super({ id, name, email, competencies });\n        this.age = age;\n    }\n}\nexports[\"default\"] = Candidate;\n\n\n//# sourceURL=webpack://frontend/./models/Candidate.ts?");

/***/ }),

/***/ "./models/Company.ts":
/*!***************************!*\
  !*** ./models/Company.ts ***!
  \***************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\nvar __importDefault = (this && this.__importDefault) || function (mod) {\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\n};\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nconst User_1 = __importDefault(__webpack_require__(/*! ./User */ \"./models/User.ts\"));\nclass Company extends User_1.default {\n}\nexports[\"default\"] = Company;\n\n\n//# sourceURL=webpack://frontend/./models/Company.ts?");

/***/ }),

/***/ "./models/User.ts":
/*!************************!*\
  !*** ./models/User.ts ***!
  \************************/
/***/ ((__unused_webpack_module, exports) => {

eval("\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nclass User {\n    constructor({ id = 0, name = \"default\", email = \"default@gmail.com\", competencies = [\"\"] } = {}) {\n        this.id = id;\n        this.name = name;\n        this.email = email;\n        this.competencies = competencies;\n    }\n}\nexports[\"default\"] = User;\n\n\n//# sourceURL=webpack://frontend/./models/User.ts?");

/***/ }),

/***/ "./scripts/main.ts":
/*!*************************!*\
  !*** ./scripts/main.ts ***!
  \*************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\nvar __importDefault = (this && this.__importDefault) || function (mod) {\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\n};\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nconst User_1 = __importDefault(__webpack_require__(/*! ../models/User */ \"./models/User.ts\"));\nconst Candidate_1 = __importDefault(__webpack_require__(/*! ../models/Candidate */ \"./models/Candidate.ts\"));\nconst Company_1 = __importDefault(__webpack_require__(/*! ../models/Company */ \"./models/Company.ts\"));\nlet candidates;\nlet companies;\nfunction get_user_logged() {\n    let user = localStorage.getItem(\"user\");\n    return user ? new User_1.default(JSON.parse(user)) : null;\n}\nfunction load() {\n    let user = get_user_logged();\n    if (!user) {\n        window.location.href = \"http://localhost:8080/templates/login.html\";\n    }\n    candidates = initial_load_candidates();\n    companies = initial_load_companies();\n    updateUserList(candidates);\n}\nfunction initial_load_candidates() {\n    let pre_load = [];\n    let stored = localStorage.getItem(\"candidates\") || \"\";\n    if (stored) {\n        const saved_candidates = JSON.parse(stored);\n        saved_candidates.forEach((candidate) => {\n            let newCandidate = new Candidate_1.default(candidate);\n            pre_load.push(newCandidate);\n        });\n        return pre_load;\n    }\n    for (let i = 0; i < 5; i++) {\n        let candidate = new Candidate_1.default({\n            id: i,\n            name: `Candidato ${i}`,\n            email: `candidato${i}@gmail.com`,\n            competencies: [\"TypeScript\", \"Java\", \"Groovy\"],\n            age: 20 + i\n        });\n        pre_load.push(candidate);\n    }\n    localStorage.setItem(\"candidates\", JSON.stringify(pre_load));\n    return pre_load;\n}\nfunction initial_load_companies() {\n    let stored = localStorage.getItem(\"companies\") || \"\";\n    if (stored) {\n        return JSON.parse(stored);\n    }\n    let pre_load = [];\n    for (let i = 0; i < 5; i++) {\n        let company = new Company_1.default({\n            id: i + 5,\n            name: `Empresa ${i}`,\n            email: `empresa${i}@gmail.com`,\n            competencies: [\"TypeScript\", \"Java\", \"Groovy\"]\n        });\n        pre_load.push(company);\n    }\n    localStorage.setItem(\"companies\", JSON.stringify(pre_load));\n    return pre_load;\n}\nfunction updateUserList(users) {\n    const userList = document.getElementById(\"userList\");\n    if (userList) {\n        userList.innerHTML = \"\";\n        users.forEach((user) => {\n            let li = document.createElement(\"li\");\n            li.className = \"user-card\";\n            li.id = `userCard${user.id}`;\n            li.innerHTML = `<h3>${user.name}</h3>`;\n            userList.appendChild(li);\n        });\n    }\n}\nload();\n\n\n//# sourceURL=webpack://frontend/./scripts/main.ts?");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module is referenced by other modules so it can't be inlined
/******/ 	var __webpack_exports__ = __webpack_require__("./scripts/main.ts");
/******/ 	
/******/ })()
;