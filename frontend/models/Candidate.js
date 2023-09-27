"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const User_1 = __importDefault(require("./User"));
class Candidate extends User_1.default {
    constructor(_id, _name, _email, _competencies, _age, _languages) {
        super(_id, _name, _email, _competencies);
        this._age = _age;
        this._languages = _languages;
    }
    get age() { return this._age; }
    get languages() { return this._languages; }
}
exports.default = Candidate;
