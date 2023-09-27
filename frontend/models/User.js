"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class User {
    constructor(_id, _name, _email, _competencies) {
        this._id = _id;
        this._name = _name;
        this._email = _email;
        this._competencies = _competencies;
    }
    get id() { return this._id; }
    get name() { return this._name; }
    get email() { return this._email; }
    get competencies() { return this._competencies; }
}
exports.default = User;
