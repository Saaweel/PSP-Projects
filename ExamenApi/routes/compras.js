const express = require("express");
const router = express.Router();

let data = require("../data/compras");

router.get("/", (req, res) => {
    res.json(data);
});

router.post("/", (req, res) => {
    data.push(req.body);

    res.json("Compra añadida");
});

router.get("/:id", (req, res) => {
    const compra = data.find(compra => compra.id === req.params.id);
    
    if (compra) {
        res.json(compra);
    } else {
        res.status(404).send("Compra no encontrada");
    }
});

router.put("/:id", (req, res) => {
    const index = data.findIndex(compra => compra.id === req.params.id);
    
    if (index !== -1) {
        data[index] = req.body;

        res.json("Compra actualizada");
    } else {
        res.status(404).send("Compra no encontrada");
    }
});

router.delete("/:id", (req, res) => {
    const index = data.findIndex(compra => compra.id === req.params.id);
    
    if (index !== -1) {
        data.splice(index, 1);

        res.json("Compra eliminada");
    } else {
        res.status(404).send("Compra no encontrada");
    }
});

module.exports = router;