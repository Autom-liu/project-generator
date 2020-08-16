package ${basePackage}.${moduleName}.web.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${basePackage}.common.enums.ErrorEnum;
import ${basePackage}.common.exception.BizException;
import ${basePackage}.common.vo.IResult;
import ${basePackage}.common.vo.PageVO;
import ${basePackage}.common.vo.Result;
import ${basePackage}.common.vo.ResultList;
import ${basePackage}.common.vo.ResultPage;
import ${basePackage}.${moduleName}.bean.${beanClassName};
import ${basePackage}.${moduleName}.dto.${dtoClassName};
import ${basePackage}.${moduleName}.query.${queryClassName};
import ${basePackage}.${moduleName}.service.${serviceClassName};
import ${basePackage}.${moduleName}.vo.${voClassName};

import java.util.List;

@RestController
@RequestMapping("/${beanClassName}")
public class ${className} {

    @Autowired
    private ${serviceClassName} apiService;

    @PostMapping("/page")
    public IResult queryPage(@RequestBody ${queryClassName} query) throws BizException {
        query.setPageFlag(true);
        PageVO<${voClassName}> pageVO = apiService.queryPage(query);
        return ResultPage.success(pageVO);
    }

    @PostMapping("/list")
    public IResult queryList(@RequestBody ${queryClassName} query) throws BizException {
        List<${voClassName}> resultList = apiService.queryList(query);
        return ResultList.success(resultList);
    }

    @PostMapping("/one/{id}")
    public IResult getOne(@PathVariable("id") Integer id) throws BizException {
        ${voClassName} result = apiService.findOne(id);
        return Result.success(result);
    }

    @PostMapping("/add")
    public IResult add(@RequestBody @Valid ${dtoClassName} entityDto) throws BizException {
        ${beanClassName} result = apiService.insert(entityDto);
        return Result.success(result);
    }

    @PostMapping("/update")
    public IResult update(@RequestBody @Valid ${dtoClassName} entityDto) throws BizException {
        Integer rows = apiService.updateByIdSelective(entityDto);
        return rows > 0 ? Result.success(rows) : IResult.error(ErrorEnum.ERRCODE_0404);
    }

    @PostMapping("/delete/{id}")
    public IResult delete(@PathVariable("id") Integer id) throws BizException {
        Integer rows = apiService.deleteById(id);
        return rows > 0 ? Result.success(rows) : IResult.error(ErrorEnum.ERRCODE_0404);
    }

}
