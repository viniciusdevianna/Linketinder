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

/***/ "./data/candidate-loader.ts":
/*!**********************************!*\
  !*** ./data/candidate-loader.ts ***!
  \**********************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\nvar __importDefault = (this && this.__importDefault) || function (mod) {\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\n};\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nconst Candidate_1 = __importDefault(__webpack_require__(/*! ../models/Candidate */ \"./models/Candidate.ts\"));\nfunction loadCandidates() {\n    let pre_load = [];\n    let stored = localStorage.getItem(\"candidates\") || \"\";\n    if (stored) {\n        const saved_candidates = JSON.parse(stored);\n        saved_candidates.forEach((candidate) => {\n            let newCandidate = new Candidate_1.default(candidate);\n            pre_load.push(newCandidate);\n        });\n        return pre_load;\n    }\n    for (let i = 0; i < 5; i++) {\n        let candidate = new Candidate_1.default({\n            id: i,\n            name: `Candidato ${i}`,\n            email: `candidato${i}@gmail.com`,\n            competencies: [\"TypeScript\", \"Java\", \"Groovy\"],\n            age: 20 + i\n        });\n        pre_load.push(candidate);\n    }\n    localStorage.setItem(\"candidates\", JSON.stringify(pre_load));\n    return pre_load;\n}\nexports[\"default\"] = loadCandidates;\n\n\n//# sourceURL=webpack://frontend/./data/candidate-loader.ts?");

/***/ }),

/***/ "./models/Candidate.ts":
/*!*****************************!*\
  !*** ./models/Candidate.ts ***!
  \*****************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\nvar __importDefault = (this && this.__importDefault) || function (mod) {\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\n};\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nconst User_1 = __importDefault(__webpack_require__(/*! ./User */ \"./models/User.ts\"));\nclass Candidate extends User_1.default {\n    constructor({ id = 0, username = \"default\", password = \"default\", name = \"default\", email = \"default@gmail.com\", competencies = [\"\"], age = 0 } = {}) {\n        super({ id, username, password, name, email, competencies });\n        this.age = age;\n    }\n}\nexports[\"default\"] = Candidate;\n\n\n//# sourceURL=webpack://frontend/./models/Candidate.ts?");

/***/ }),

/***/ "./models/User.ts":
/*!************************!*\
  !*** ./models/User.ts ***!
  \************************/
/***/ ((__unused_webpack_module, exports) => {

eval("\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nclass User {\n    constructor({ id = 0, username = \"default\", password = \"default\", name = \"default\", email = \"default@gmail.com\", competencies = [\"\"] } = {}) {\n        this.id = id;\n        this.username = username;\n        this.password = password;\n        this.name = name;\n        this.email = email;\n        this.competencies = competencies;\n    }\n}\nexports[\"default\"] = User;\n\n\n//# sourceURL=webpack://frontend/./models/User.ts?");

/***/ }),

/***/ "./scripts/login.ts":
/*!**************************!*\
  !*** ./scripts/login.ts ***!
  \**************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

eval("\nvar __importDefault = (this && this.__importDefault) || function (mod) {\n    return (mod && mod.__esModule) ? mod : { \"default\": mod };\n};\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\nconst candidate_loader_1 = __importDefault(__webpack_require__(/*! ../data/candidate-loader */ \"./data/candidate-loader.ts\"));\nconst form = document.getElementById(\"loginForm\");\nconst signin = document.getElementById(\"signinLink\");\nif (form) {\n    form.addEventListener(\"submit\", function (event) {\n        event.preventDefault();\n        let username = document.getElementById(\"username\").value;\n        let password = document.getElementById(\"password\").value;\n        let userList = (0, candidate_loader_1.default)();\n        let user = userList.filter((user) => username === user.username && password === user.password);\n        if (!user[0]) {\n            alert(\"Usuário ou senha incorretos\");\n        }\n        else {\n            localStorage.setItem(\"user\", JSON.stringify(user[0]));\n            window.location.href = \"http://localhost:8080/\";\n        }\n    });\n}\nif (signin) {\n    signin.onclick = (e) => [\n        window.location.href = \"http://localhost:8080/templates/signin.html\"\n    ];\n}\n\n\n//# sourceURL=webpack://frontend/./scripts/login.ts?");

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
/******/ 	var __webpack_exports__ = __webpack_require__("./scripts/login.ts");
/******/ 	
/******/ })()
;