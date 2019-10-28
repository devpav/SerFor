package by.devpav.serfor.resources

import by.devpav.serfor.domain.dtos.BasicEntityDTO

interface BasicEntityResource<T : BasicEntityDTO> : Resource<T, Long>
